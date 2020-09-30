package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.configuration.ErrorMessage;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component(PREFIX + "AccessDeniedHandler")
public class ModellBahnAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private final ObjectMapper mapper;

    private final AccessDeniedHandler delegate = new AccessDeniedHandlerImpl();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        String requestUri = request.getRequestURI();

        if (requestUri.startsWith(ApiPaths.API_ROOT) ||
            requestUri.startsWith(ApiPaths.MANAGEMENT_ROOT)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
            if (auth != null) {
                log.warn("User: {} attempted to access the protected URL: {}", auth.getName(), request.getRequestURI());
            }

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter()
                    .write(
                        mapper.writeValueAsString(
                            ErrorMessage.builder()
                                        .code(HttpStatus.FORBIDDEN.value())
                                        .path(requestUri)
                                        .timestamp(System.currentTimeMillis())
                                        .message(HttpStatus.FORBIDDEN.name())
                                        .build()));
        } else {
            delegate.handle(request, response, exception);
        }
    }
}
