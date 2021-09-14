package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.security.WebSecurityConfig.isAdmin;

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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.metadata.ConstraintDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Charsets;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.i18n.LocaleSetter;
import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.security.EmailService;
import com.linepro.modellbahn.security.WebSecurityConfig;
import com.linepro.modellbahn.security.password.PasswordProcessor;
import com.linepro.modellbahn.security.user.UserModel.UserModelBuilder;
import com.linepro.modellbahn.service.criterion.PageCriteria;
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
    protected Integer accountExpiry;

    @Value("${com.linepro.modellbahn.user.login-attempts:5}")
    protected Integer loginAttempts;

    @Value("${com.linepro.modellbahn.user.password-aging:60}")
    protected Integer passwordAging;

    @Value("${com.linepro.modellbahn.user.confirmation-time:24}")
    protected Integer confirmationTime;

    @Value("${com.linepro.modellbahn.user.reset-time:1}")
    protected Integer resetTime;

    @Value("${com.linepro.modellbahn.noreply}")
    protected String noReply;

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

    @Autowired
    private final LocaleSetter localeSetter;

    public Optional<UserModel> get(String name, Authentication authentication) {
        return userRepository.findByName(name).map(u -> toModel(u, isAdmin(authentication)));
    }

    public Page<UserModel> search(UserCriterion model, PageCriteria paging, Authentication authentication) {
        Page<User> found = userRepository.findAll(model, paging.getPageRequest());
        return found.map(u -> toModel(u, isAdmin(authentication)));
    }

    public UserResponse update(String name, UserRequest model, HttpSession session, Authentication authentication) {
        User user = getUser(name, authentication);

        Set<ConstraintViolation<?>> errors = new HashSet<>();

        if (StringUtils.hasText(model.getEmail()) && !user.getEmail().equals(model.getEmail())) {
            // eMail change; ensure that we aren't using it already
            if (userRepository.findByEmail(model.getEmail()).isPresent()) {
                errors.add(userError(ApiMessages.USER_EXISTS, model.getEmail(), user));
            }
        }

        if (errors.isEmpty()) {
            user = fromModel(user, model, isAdmin(authentication));

            errors.addAll(validator.validate(user));
        }

        if (!errors.isEmpty()) {
            return userResponse(user, HttpStatus.BAD_REQUEST, errors);
        }

        localeSetter.setLocale(session, user.getLocale()); // TODO: model.locale ? : Should really be authentication's locale

        return userResponse(userRepository.saveAndFlush(user), HttpStatus.ACCEPTED, translator.getMessage(ApiMessages.USER_UPDATED, name));
    }

    public boolean delete(String name, Authentication authentication) {
        User user = getUser(name, authentication);

        userRepository.delete(user);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).map(u -> UserDetailsImpl.builder().user(u).isAccountNonExpired(isAccountNonExpired(u))
                        .isAccountNonLocked(isAccountNonLocked(u)).isCredentialsNonExpired(isCredentialsNonExpired(u)).authorities(getAuthorities(u))

                        .build()).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserResponse register(HttpSession session, UserRequest request) {
        Set<ConstraintViolation<?>> errors = new HashSet<>();

        localeSetter.setLocale(session, request.getLocale());

        errors.addAll(passwordProcessor.validate(request.getPassword()));

        User user = User.builder()
                        .name(request.getUsername())
                        .email(request.getEmail())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(passwordProcessor.encode(request.getPassword())).locale(request.getLocale())
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

        return userResponse(user, HttpStatus.BAD_REQUEST, errors.stream().map(v -> v.getMessage()).collect(Collectors.joining("\\n")));
    }

    public UserResponse confirmRegistration(String token) {
        Optional<User> user = userRepository.findByConfirmationToken(UUID.fromString(token));

        if (user.isPresent()) {
            if (user.get().getConfirmationExpires().isBefore(LocalDateTime.now())) {
                return userResponse(user.get(), HttpStatus.BAD_REQUEST, translator.getMessage(ApiMessages.TOKEN_EXPIRED, token));
            }

            User found = user.get();
            clearConfirmationToken(found);
            found.setEnabled(true);

            userRepository.saveAndFlush(found);

            return userResponse(user.get(), HttpStatus.ACCEPTED, translator.getMessage(ApiMessages.USER_CONFIRMED, found.getEmail()));
        }

        return userResponse(user.orElse(null), HttpStatus.BAD_REQUEST, translator.getMessage(ApiMessages.INVALID_TOKEN, token));
    }

    public UserResponse forgotPassword(String email) {
        Optional<User> found = userRepository.findByEmail(email);

        if (found.isPresent()) {
            User user = found.get();

            if (user.getConfirmationToken() != null && !user.getEnabled()) {
                return requestConfirmation(user);
            }

            setConfirmationToken(user, resetTime);

            userRepository.saveAndFlush(user);

            String resetLink = generateLink(user, UserController.RESET_PASSWORD);

            emailUser(user, ApiMessages.FORGOT_EMAIL_SUBJECT, ApiMessages.FORGOT_EMAIL_BODY, resetLink);

            return userResponse(user, HttpStatus.ACCEPTED, translator.getMessage(ApiMessages.FORGOT_EMAIL_SENT, user.getEmail()));
        }

        return userResponse(found.orElse(null), HttpStatus.BAD_REQUEST, translator.getMessage(ApiMessages.INVALID_USER, email));
    }

    public UserResponse resetPassword(String token, String password) {
        Optional<User> found = userRepository.findByConfirmationToken(UUID.fromString(token));

        if (found.isPresent()) {
            User user = found.get();

            if (user.getConfirmationExpires().isBefore(LocalDateTime.now())) {
                return userResponse(user, HttpStatus.BAD_REQUEST, translator.getMessage(ApiMessages.TOKEN_EXPIRED, token));
            }

            return updatePassword(password, user);
        }

        return userResponse(found.orElse(null), HttpStatus.BAD_REQUEST, translator.getMessage(ApiMessages.INVALID_TOKEN, token));
    }

    public UserResponse changePassword(String name, String password, Authentication authentication) {
        User user = getUser(name, authentication);

        return updatePassword(password, user);
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

    protected UserModel toModel(User u, boolean isAdmin) {
        UserModelBuilder builder = UserModel.builder().username(u.getName()).email(u.getEmail()).firstName(u.getFirstName()).lastName(u.getLastName())
                        .locale(u.getLocale()).enabled(u.getEnabled()).lastLogin(u.getLastLogin());

        if (isAdmin) {
            builder = builder.passwordAging(u.getPasswordAging()).passwordChanged(u.getPasswordChanged())
                            .confirmationExpires(u.getConfirmationExpires()).loginAttempts(u.getLoginAttempts()).roles(u.getRoles());
        }

        return builder.build();
    }

    protected User fromModel(User user, UserRequest request, boolean isAdmin) {
        if (isAdmin) {
            user.setPasswordAging(request.getPasswordAging());
            user.setLoginAttempts(request.getLoginAttempts());
            if (request.getEnabled() != null) user.setEnabled(request.getEnabled());
            if (!CollectionUtils.isEmpty(request.getRoles())) user.setRoles(request.getRoles());
        } else {
            if (StringUtils.hasText(request.getEmail())) user.setEmail(request.getEmail());
            if (StringUtils.hasText(request.getFirstName())) user.setFirstName(request.getFirstName());
            if (StringUtils.hasText(request.getLastName())) user.setLastName(request.getLastName());
            if (StringUtils.hasText(request.getLocale())) user.setLocale(request.getLocale());
        }

        return user;
    }

    protected UserResponse requestConfirmation(User user) {
        setConfirmationToken(user, confirmationTime);

        userRepository.saveAndFlush(user);

        String confirmationLink = generateLink(user, UserController.CONFIRM_REGISTRATION);

        emailUser(user, ApiMessages.REGISTER_EMAIL_SUBJECT, ApiMessages.REGISTER_EMAIL_BODY, confirmationLink);

        return userResponse(user, HttpStatus.CREATED, translator.getMessage(ApiMessages.REGISTER_EMAIL_SENT, user.getEmail()));
    }

    protected String generateLink(User user, String path) {
        return ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).queryParam(ApiNames.TOKEN, user.getConfirmationToken()).build()
                        .toString();
    }

    protected void emailUser(User user, String subject, String body, Object... params) {
        try {
            MimeMessage message = emailService.createMessage();

            List<Object> values = new ArrayList<Object>();
            values.addAll(Arrays.asList(params));
            values.add(user.getName());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(LocaleContextHolder.getLocale())
                            .withZone(ZoneId.systemDefault());
            values.add(user.getConfirmationExpires().format(formatter));

            MimeMessageHelper helper = new MimeMessageHelper(message, false, Charsets.ISO_8859_1.name());
            helper.setTo(user.getEmail());
            helper.setFrom(noReply);
            helper.setSubject(translator.getMessage(subject));
            helper.setText(translator.getMessage(body, (Object[]) values.toArray()), true);

            emailService.sendEmail(message);

            log.info("{}", params);
        } catch (Exception e) {
            log.error("Error sending email {} {}: {}", user.getEmail(), subject, e.getMessage(), e);

            ModellBahnException.raise(ApiMessages.MAIL_ERROR, e).addValue(user.getEmail());
        }
    }

    protected UserResponse updatePassword(String password, User user) {
        Set<ConstraintViolation<?>> errors = passwordProcessor.validate(password)
                                                              .stream()
                                                              .map(c -> (ConstraintViolation<?>) c)
                                                              .collect(Collectors.toSet());

        if (errors.isEmpty()) {
            clearConfirmationToken(user);
            user.setEnabled(true);
            user.setPassword(passwordProcessor.encode(password));
            user.setPasswordChanged(LocalDateTime.now());

            // Save user
            user = userRepository.saveAndFlush(user);

            return userResponse(user, HttpStatus.ACCEPTED, translator.getMessage(ApiMessages.PASSWORD_CHANGED, user.getEmail()));
        }

        return userResponse(user, HttpStatus.BAD_REQUEST, errors);
    }

    protected User getUser(String name, Authentication authentication) {
        Optional<User> found = userRepository.findByName(name);

        if (!name.equals(authentication.getName()) || !found.isPresent()) {
            throw new AccessDeniedException(translator.getMessage(ApiMessages.INVALID_USER, name));
        }

        User user = found.get();
        return user;
    }

    protected UserResponse userResponse(User user, HttpStatus status, Set<ConstraintViolation<?>> errors) {
        return userResponse(user, status, errors.stream().map(v -> v.getMessage()).collect(Collectors.joining("\\n")));
    }
    
    protected UserResponse userResponse(User user, HttpStatus status, String message) {
        return UserResponse.builder()
                           .user(toModel(user, false))
                           .timestamp(System.currentTimeMillis())
                           .status(status.value())
                           .message(message)
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
        return Optional.ofNullable(user.getRoles()).map(r -> r.stream().map(o -> new SimpleGrantedAuthority(o)).collect(Collectors.toSet()))
                        .orElse(Collections.emptySet());
    }
}
