package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component(PREFIX + "AuthenticationListener")
@RequiredArgsConstructor
public class UserAuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent) {
        if (appEvent instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;

            Optional<User> found = userRepository.findByName(event.getAuthentication().getName());

            if (found.isPresent()) {
                User user = found.get();

                user.setLoginFailures(0);
                user.setLastLogin(LocalDateTime.now());

                userRepository.saveAndFlush(user);
            }
        } else if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;

            Optional<User> found = userRepository.findByName(event.getAuthentication().getName());

            if (found.isPresent()) {
                User user = found.get();

                user.setLoginFailures(user.getLoginFailures() == null ? 1 : user.getLoginFailures() + 1);

                userRepository.saveAndFlush(user);
            }
        }
    }
}
