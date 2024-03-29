package com.linepro.modellbahn.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.configuration.JsonRequestFilter;
import com.linepro.modellbahn.configuration.UserMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonAccessDeniedHandler {

    private final ObjectMapper mapper;

    private final JsonRequestFilter requestFilter;

    public boolean isDenied(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
        if (requestFilter.isJsonRequest(request)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
            if (auth != null) {
                log.warn("User: {} attempted to access the protected URL using JSON: {}", auth.getName(), request.getRequestURI());
            }

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            HttpStatus status = (exception instanceof AuthenticationException) ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN;

            response.setStatus(status.value());
            response.getWriter()
                    .write(
                        mapper.writeValueAsString(
                            UserMessage.builder()
                                       .path(request.getRequestURI())
                                       .timestamp(System.currentTimeMillis())
                                       .status(status.value())
                                       .message(status.name())
                                       .build()
                            )
                        );

            return true;
        }

        return false;
    }
}
