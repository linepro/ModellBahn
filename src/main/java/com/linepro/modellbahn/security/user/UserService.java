package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Charsets;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.security.EmailService;
import com.linepro.modellbahn.security.WebSecurityConfig;
import com.linepro.modellbahn.security.password.PasswordProcessor;
import com.linepro.modellbahn.security.password.RawPassword;
import com.linepro.modellbahn.security.user.UserModel.UserModelBuilder;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX + "UserService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

    @Value("${com.linepro.modellbahn.user.expiry:720}")
    private Integer accountExpiry;

    @Value("${com.linepro.modellbahn.user.login-attempts:5}")
    private Integer loginAttempts;

    @Value("${com.linepro.modellbahn.user.password-aging:60}")
    private Integer passwordAging;

    @Value("${com.linepro.modellbahn.user.confirmation-time:24}")
    protected Integer confirmationTime;

    @Value("${com.linepro.modellbahn.user.reset-time:1}")
    protected Integer resetTime;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordProcessor passwordProcessor;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final Validator validator;

    @Autowired
    private final MessageTranslator translator;

    @Value("${com.linepro.modellbahn.user.confirmUrl:}")
    private final String confirmationUrl;

    @Value("${com.linepro.modellbahn.user.resetUrl:}")
    private final String resetUrl;

    @Value("${com.linepro.modellbahn.noreply}")
    private final String noReply;

    public Optional<UserModel> get(String name, Authentication authentication) {
        return userRepository.findByName(name)
                             .map(u -> toModel(u, isAdmin(authentication)));
    }

    public Page<UserModel> search(Optional<UserModel> model, Optional<Integer> pageNumber, Optional<Integer> pageSize, Authentication authentication) {
        Pageable pageable = (pageNumber.isPresent() || pageSize.isPresent())  ? 
                        PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE)) : 
                        Pageable.unpaged();

        Page<User> found;
        
        if (model.isPresent()) {
            Example<User> user = Example.of(fromModel(new User(), model.get(), isAdmin(authentication)));

            found = userRepository.findAll(user, pageable);
        } else {
            found = userRepository.findAll(pageable);
        }
        
        return found.map(u -> toModel(u, isAdmin(authentication)));
    }

    public Optional<UserModel> update(String name, UserModel model, Authentication authentication) {
        return userRepository.findByName(name)
                             .map(u -> {
                                 if (!name.equals(authentication.getName())) {
                                     throw new AccessDeniedException(translator.getMessage(ApiMessages.INVALID_USER, name));
                                 }

                                 Set<ConstraintViolation<?>> errors = new HashSet<>();

                                 if (StringUtils.hasText(model.getEmail()) && !u.getEmail().equals(model.getEmail())) {
                                     // eMail change; ensure that we aren't using it already
                                     if (userRepository.findByEmail(model.getEmail()).isPresent()) {
                                         errors.add(userError(ApiMessages.USER_EXISTS, model.getEmail(), u));
                                     }
                                 }

                                 if (errors.isEmpty()) {
                                     u = fromModel(u, model, isAdmin(authentication));

                                     errors.addAll(validator.validate(u));
                                 }

                                 if (!errors.isEmpty()) {
                                     throw new ConstraintViolationException(errors);
                                 }

                                 return toModel(userRepository.saveAndFlush(u), isAdmin(authentication));
                             });
    }

    public boolean delete(String name, Authentication authentication) {
        return userRepository.findByName(name)
                             .map(e -> {
                                 if (!name.equals(authentication.getName())) {
                                     throw new AccessDeniedException(translator.getMessage(ApiMessages.INVALID_USER, name));
                                 }

                                 userRepository.delete(e);

                                 return true;
                             })
                             .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                             .map(u -> UserDetailsImpl.builder()
                                                      .user(u)
                                                      .isAccountNonExpired(isAccountNonExpired(u))
                                                      .isAccountNonLocked(isAccountNonLocked(u))
                                                      .isCredentialsNonExpired(isCredentialsNonExpired(u))
                                                      .authorities(getAuthorities(u))
                                                      .build())
                             .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserMessage register(UserModel model) {
        Set<ConstraintViolation<?>> errors = new HashSet<>();

        errors.addAll(passwordProcessor.validate(model.getPassword()));

        User user = User.builder()
                        .name(model.getName())
                        .email(model.getEmail())
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .password(passwordProcessor.encode(model.getPassword()))
                        .locale(model.getLocale())
                        .passwordAging(passwordAging)
                        .loginAttempts(loginAttempts)
                        .enabled(false)
                        .roles(Collections.singletonList(WebSecurityConfig.USER_AUTHORITY))
                        .build();

        errors.addAll(validator.validate(user));

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.add(userError(ApiMessages.USER_EXISTS, user.getEmail(), user));
        }

        if (userRepository.findByName(user.getName()).isPresent()) {
            errors.add(userError(ApiMessages.USER_EXISTS, user.getName(), user));
        }

        if (CollectionUtils.isEmpty(errors)) {
            return requestConfirmation(user);
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(errors.stream().map(v -> v.getMessage()).collect(Collectors.joining("\\n")))
                          .build();
    }

    public UserMessage confirmRegistration(String token) {
        Optional<User> user = userRepository.findByConfirmationToken(UUID.fromString(token));

        if (user.isPresent()) {
            if (user.get().getConfirmationExpires().isBefore(LocalDateTime.now())) {
                return UserMessage.builder()
                                  .timestamp(System.currentTimeMillis())
                                  .status(HttpStatus.BAD_REQUEST.value())
                                  .message(translator.getMessage(ApiMessages.TOKEN_EXPIRED, token))
                                  .build();
            }

            User found = user.get();
            clearConfirmationToken(found);
            found.setEnabled(true);

            userRepository.saveAndFlush(found);

            return UserMessage.builder()
                              .timestamp(System.currentTimeMillis())
                              .status(HttpStatus.ACCEPTED.value())
                              .message(translator.getMessage(ApiMessages.USER_CONFIRMED, found.getEmail()))
                              .build();
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_TOKEN, token))
                          .build();
    }

    public UserMessage forgotPassword(String email) {
        Optional<User> found = userRepository.findByEmail(email);

        if (found.isPresent()) {
            User user = found.get();

            if (user.getConfirmationToken() != null && !user.getEnabled()) {
                return requestConfirmation(user);
            }

            setConfirmationToken(user, resetTime);

            userRepository.saveAndFlush(user);

            String resetLink = generateLink(user, resetUrl);

            emailUser(user, ApiMessages.FORGOT_EMAIL_SUBJECT, ApiMessages.FORGOT_EMAIL_BODY, resetLink);

            return UserMessage.builder()
                              .timestamp(System.currentTimeMillis())
                              .status(HttpStatus.ACCEPTED.value())
                              .message(translator.getMessage(ApiMessages.FORGOT_EMAIL_SENT, user.getEmail()))
                              .build();
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_USER, email))
                          .build();
    }

    public UserMessage resetPassword(String token, String password) {
        Optional<User> found = userRepository.findByConfirmationToken(UUID.fromString(token));

        if (found.isPresent()) {
            User user = found.get();

            if (user.getConfirmationExpires().isBefore(LocalDateTime.now())) {
                return UserMessage.builder()
                                  .timestamp(System.currentTimeMillis())
                                  .status(HttpStatus.BAD_REQUEST.value())
                                  .message(translator.getMessage(ApiMessages.TOKEN_EXPIRED, token))
                                  .build();
            }

            return updatePassword(password, user);
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_TOKEN, token))
                          .build();
    }

    public UserMessage changePassword(String name, String password, Authentication authentication) {
        Optional<User> found = userRepository.findByName(name);

        if (found.isPresent()) {
            User user = found.get();

            if (!name.equals(authentication.getName())) {
                throw new AccessDeniedException(translator.getMessage(ApiMessages.INVALID_USER, name));
            }

            return updatePassword(password, user);
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_USER, name))
                          .build();
    }

    private void setConfirmationToken(User user, int hours) {
        user.setConfirmationToken(UUID.randomUUID());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusHours(hours);
        user.setConfirmationExpires(expiry);
    }

    private void clearConfirmationToken(User user) {
        user.setConfirmationToken(null);
        user.setConfirmationExpires(null);
    }

    protected boolean isAdmin(Authentication authentication) {
        return (authentication.getAuthorities().contains(WebSecurityConfig.ADMIN));
    }

    protected UserModel toModel(User u, boolean isAdmin) {
        UserModelBuilder builder = UserModel.builder()
                           .name(u.getName())
                           .email(u.getEmail())
                           .firstName(u.getFirstName())
                           .lastName(u.getLastName())
                           .locale(u.getLocale())
                           .enabled(u.getEnabled())
                           .lastLogin(u.getLastLogin());

             if (isAdmin) {
                   builder = builder.passwordAging(u.getPasswordAging())
                           .passwordChanged(u.getPasswordChanged())
                           .confirmationExpires(u.getConfirmationExpires())
                           .loginAttempts(u.getLoginAttempts())
                           .roles(u.getRoles());
            }

           return builder.build();
    }

    protected User fromModel(User user, UserModel model, boolean isAdmin) {
        if (isAdmin) {
            user.setPasswordAging(model.getPasswordAging());
            user.setLoginAttempts(model.getLoginAttempts());
            if (model.getEnabled() != null) user.setEnabled(model.getEnabled());
            if (!CollectionUtils.isEmpty(model.getRoles())) user.setRoles(model.getRoles());
        } else {
                if (StringUtils.hasText(model.getEmail())) user.setEmail(model.getEmail());
                if (StringUtils.hasText(model.getFirstName())) user.setFirstName(model.getFirstName());
                if (StringUtils.hasText(model.getLastName())) user.setLastName(model.getLastName());
                if (StringUtils.hasText(model.getLocale())) user.setLocale(model.getLocale());
        }

        return user;
    }

    protected UserMessage requestConfirmation(User user) {
        setConfirmationToken(user, confirmationTime);

        userRepository.saveAndFlush(user);

        String confirmationLink = generateLink(user, confirmationUrl);

        emailUser(user, ApiMessages.REGISTER_EMAIL_SUBJECT, ApiMessages.REGISTER_EMAIL_BODY, confirmationLink);

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.CREATED.value())
                          .message(translator.getMessage(ApiMessages.REGISTER_EMAIL_SENT, user.getEmail()))
                          .build();
    }

    protected String generateLink(User user, String path) {
        return UriComponentsBuilder.fromPath(path)
                                   .queryParam(ApiNames.TOKEN, user.getConfirmationToken())
                                   .build()
                                   .toString();
    }

    protected void emailUser(User user, String subject, String body, Object... params) {
        try {
            MimeMessage message = emailService.createMessage();

            List<Object> values = new ArrayList<Object>();
            values.addAll(Arrays.asList(params));
            values.add(user.getName());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                                                           .withLocale(LocaleContextHolder.getLocale())
                                                           .withZone(ZoneId.systemDefault());
            values.add(user.getConfirmationExpires().format(formatter));

            MimeMessageHelper helper = new MimeMessageHelper(message, false, Charsets.ISO_8859_1.name());
            helper.setTo(user.getEmail());
            helper.setFrom(noReply);
            helper.setSubject(translator.getMessage(subject));
            helper.setText(translator.getMessage(body, (Object[]) values.toArray()), true);

            emailService.sendEmail(message);
        } catch (Exception e) {
            log.error("Error sending email {} {}: {}", user.getEmail(), subject, e.getMessage(), e);

            ModellBahnException.raise(ApiMessages.MAIL_ERROR, e)
                               .addValue(user.getEmail());
        }
    }

    protected UserMessage updatePassword(String password, User found) {
        Set<ConstraintViolation<RawPassword>> errors = passwordProcessor.validate(password);

        if (errors.isEmpty()) {
            clearConfirmationToken(found);
            found.setEnabled(true);
            found.setPassword(passwordProcessor.encode(password));
            found.setPasswordChanged(LocalDateTime.now());

            // Save user
            userRepository.saveAndFlush(found);

            return UserMessage.builder()
                              .timestamp(System.currentTimeMillis())
                              .status(HttpStatus.ACCEPTED.value())
                              .message(translator.getMessage(ApiMessages.PASSWORD_CHANGED, found.getEmail()))
                              .build();
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(errors.stream().map(v -> v.getMessage()).collect(Collectors.joining("\\n")))
                          .build();
    }

    protected ConstraintViolation<User> userError(String message, String value, User user) {
        return new ConstraintViolation<User>() {
            @Override
            public String getMessage() {
                return translator.getMessage(message, value);
            }

            @Override
            public String getMessageTemplate() {
                return message;
            }

            @Override
            public User getRootBean() {
                return user;
            }

            @Override
            public Class<User> getRootBeanClass() {
                return User.class;
            }

            @Override
            public Object getInvalidValue() {
                return value;
            }


            @Override
            public <U> U unwrap(Class<U> type) {
                return null;
            }

            @Override
            public Object getLeafBean() {
                return null;
            }

            @Override
            public Object[] getExecutableParameters() {
                return null;
            }

            @Override
            public Object getExecutableReturnValue() {
                return null;
            }

            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() {
                return null;
            }

            @Override
            public Path getPropertyPath() {
                return null;
            }
        };
    }

    public boolean isAccountNonExpired(User user) {
        if (user.getLastLogin() != null) {
            return Duration.between(user.getLastLogin(), LocalDateTime.now()).toDays() < accountExpiry;
        }

        return true;
    }

    public boolean isAccountNonLocked(User user) {
        if (user.getLoginFailures() != null) {
            if (user.getLoginAttempts() != null) {
                return user.getLoginFailures() < user.getLoginAttempts();
            }
        }

        return true;
    }

    public boolean isCredentialsNonExpired(User user) {
        if (user.getPasswordAging() != null) {
            if (user.getPasswordChanged() != null) {
                return Duration.between(user.getPasswordChanged(), LocalDateTime.now()).toDays() < user.getPasswordAging();
            }

            return false;
        }

        return true;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(User user) {
        return Optional.ofNullable(user.getRoles())
                       .map(r -> r.stream()
                                  .map(o -> new SimpleGrantedAuthority(o))
                                  .collect(Collectors.toSet()))
                       .orElse(Collections.emptySet());
    }
}
