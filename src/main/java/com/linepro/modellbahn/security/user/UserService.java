package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.linepro.modellbahn.security.password.PasswordProcessor;
import com.linepro.modellbahn.security.password.RawPassword;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang3.ArrayUtils;

@Slf4j
@Service(PREFIX + "UserService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

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

    @Value("${com.linepro.modellbahn.user.confirmUrl}")
    private final String confirmationUrl;

    @Value("${com.linepro.modellbahn.user.resetUrl}")
    private final String resetUrl;

    @Value("${com.linepro.modellbahn.noreply}")
    private final String noReply;

    public Optional<UserModel> get(String name) {
        return userRepository.findByName(name)
                             .map(this::toModel);
    }

    public Page<UserModel> search(Optional<UserModel> model, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Pageable pageable = (pageNumber.isPresent() || pageSize.isPresent())  ? 
                        PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE)) : 
                        Pageable.unpaged();

        if (model.isPresent()) {
            Example<User> user = Example.of(fromModel(new User(), model.get()));

            return userRepository.findAll(user, pageable).map(this::toModel);
        }

        return userRepository.findAll(pageable).map(this::toModel);
    }

    public Optional<UserModel> update(String name, UserModel model) {
        return userRepository.findByName(name)
                             .map(u -> {
                                 Set<ConstraintViolation<?>> errors = new HashSet<>();

                                 if (StringUtils.hasText(model.getEmail()) && !u.getEmail().equals(model.getEmail())) {
                                     // eMail change; ensure that we aren't using it already
                                     if (userRepository.findByEmail(model.getEmail()).isPresent()) {
                                         errors.add(userError(ApiMessages.USER_EXISTS, model.getEmail(), u));
                                     }
                                 }

                                 if (errors.isEmpty()) {
                                     u = fromModel(u, model);

                                     errors.addAll(validator.validate(u));
                                 }

                                 if (!errors.isEmpty()) {
                                     throw new ConstraintViolationException(errors);
                                 }

                                 return toModel(userRepository.saveAndFlush(u));
                             });
    }

    public boolean delete(String name) {
        return userRepository.findByName(name)
                             .map(e -> {
                                 userRepository.delete(e);
                                 return true;
                             })
                             .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
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
                        .enabled(false)
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
            User found = user.get();
            found.clearConfirmationToken();
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

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User found = user.get();

            if (found.getConfirmationToken() != null && !found.isEnabled()) {
                return requestConfirmation(found);
            }

            found.setResetToken(UUID.randomUUID());

            userRepository.saveAndFlush(found);

            String resetLink = generateLink(found, resetUrl, found.getResetToken());

            emailUser(found, ApiMessages.FORGOT_EMAIL_SUBJECT, ApiMessages.FORGOT_EMAIL_BODY, resetLink);

            return UserMessage.builder()
                              .timestamp(System.currentTimeMillis())
                              .status(HttpStatus.ACCEPTED.value())
                              .message(translator.getMessage(ApiMessages.FORGOT_EMAIL_SENT, found.getEmail()))
                              .build();
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_USER, email))
                          .build();
    }

    public UserMessage resetPassword(String token, String password) {

        Optional<User> user = userRepository.findByResetToken(UUID.fromString(token));

        if (user.isPresent()) {
            return updatePassword(password, user.get());
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_TOKEN, token))
                          .build();
    }

    public UserMessage changePassword(String name, String password) {

        Optional<User> user = userRepository.findByName(name);

        if (user.isPresent()) {
            return updatePassword(password, user.get());
        }

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.BAD_REQUEST.value())
                          .message(translator.getMessage(ApiMessages.INVALID_USER, name))
                         .build();
    }

    protected UserModel toModel(User u) {
        return UserModel.builder()
                           .name(u.getName())
                           .email(u.getEmail())
                           .firstName(u.getFirstName())
                           .lastName(u.getLastName())
                           .locale(u.getLocale())
                           .enabled(u.isEnabled())
                           .lastLogin(u.getLastLogin())
                           .build();
    }

    protected User fromModel(User user, UserModel model) {
        if (StringUtils.hasText(model.getEmail())) user.setEmail(model.getEmail());
        if (StringUtils.hasText(model.getFirstName())) user.setFirstName(model.getFirstName());
        if (StringUtils.hasText(model.getLastName())) user.setLastName(model.getLastName());
        if (StringUtils.hasText(model.getLocale())) user.setLocale(model.getLocale());
        return user;
    }

    protected UserMessage requestConfirmation(User user) {
        user.setConfirmationToken(UUID.randomUUID());

        userRepository.saveAndFlush(user);

        String confirmationLink = generateLink(user, confirmationUrl, user.getConfirmationToken());

        emailUser(user, ApiMessages.REGISTER_EMAIL_SUBJECT, ApiMessages.REGISTER_EMAIL_BODY, confirmationLink);

        return UserMessage.builder()
                          .timestamp(System.currentTimeMillis())
                          .status(HttpStatus.CREATED.value())
                          .message(translator.getMessage(ApiMessages.REGISTER_EMAIL_SENT, user.getEmail()))
                          .build();
    }

    protected String generateLink(User user, String path, UUID token) {
        return UriComponentsBuilder.fromPath(path)
                                   .queryParam(ApiNames.TOKEN, token)
                                   .build()
                                   .toString();
    }

    protected void emailUser(User user, String subject, String body, String... params) {
        try {
            MimeMessage message = emailService.createMessage();

            List<Object> values = new ArrayList<Object>();
            values.addAll(Arrays.asList(params));
            values.add(user.getName());
            
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
            found.setPassword(passwordProcessor.encode(password));
            found.setPasswordChanged(LocalDateTime.now());
            found.clearResetToken();

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
}
