package com.linepro.modellbahn.security.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.linepro.modellbahn.i18n.LocaleSetter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ModellBahnAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired 
    private final HttpSession httpSession;

    @Autowired
    private final LocaleSetter localeSetter;

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        Authentication success = super.createSuccessAuthentication(principal, authentication, user);

        localeSetter.setLocale(httpSession, ((UserDetailsImpl) user).getUser().getLocale());

        return success;
    }


}
