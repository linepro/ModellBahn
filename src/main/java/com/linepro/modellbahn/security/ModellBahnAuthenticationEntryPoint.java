package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import static com.linepro.modellbahn.security.user.UserController.LOGIN_ENDPOINT;

@Component(PREFIX + "AuthenticationEntryPoint")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", description = "Basic Authentication", paramName = HttpHeaders.AUTHORIZATION, in = SecuritySchemeIn.HEADER, scheme = "Basic")
public class ModellBahnAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private final JsonAccessDeniedHandler handler;

    public ModellBahnAuthenticationEntryPoint(JsonAccessDeniedHandler handler) {
        super(LOGIN_ENDPOINT);

        this.handler = handler;
    }

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException, ServletException {
        if (!handler.isDenied(request, response, authEx)) {
            // Don't popup login forms for JSON requests...
            super.commence(request, response, authEx);
        }
    }
}
