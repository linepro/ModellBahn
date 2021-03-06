package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "AuthenticationEntryPoint")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", description = "Basic Authentication", paramName = HttpHeaders.AUTHORIZATION, in = SecuritySchemeIn.HEADER, scheme = "Basic")
public class ModellBahnAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private final ObjectMapper mapper;

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        String requestUri = request.getRequestURI();

        if (requestUri.startsWith(ApiPaths.API_ROOT) || requestUri.startsWith(ApiPaths.MANAGEMENT_ROOT)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter()
                    .write(
                        mapper.writeValueAsString(
                            UserMessage.builder()
                                       .path(requestUri)
                                       .timestamp(System.currentTimeMillis())
                                       .message(HttpStatus.UNAUTHORIZED.name())
                                       .build()));
        } else {
            response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            writer.println("HTTP Status 401 - " + authEx.getMessage());
        }
    }

    public void afterPropertiesSet() {
        setRealmName("ModellBahn");
        super.afterPropertiesSet();
    }
}
