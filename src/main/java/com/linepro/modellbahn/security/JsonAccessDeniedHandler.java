package com.linepro.modellbahn.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonAccessDeniedHandler {

    private final ObjectMapper mapper;
    
    public boolean isDenied(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
        if (isJsonRequest(request)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
            if (auth != null) {
                log.warn("User: {} attempted to access the protected URL using JSON: {}", auth.getName(), request.getRequestURI());
            }

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter()
                    .write(
                        mapper.writeValueAsString(
                            UserMessage.builder()
                                        .path(request.getRequestURI())
                                        .timestamp(System.currentTimeMillis())
                                        .message(HttpStatus.FORBIDDEN.name())
                                        .build()));

            return true;
        }

        return false;
    }

    public boolean isJsonRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();

        return requestUri.startsWith(ApiPaths.API_ROOT) ||
               requestUri.startsWith(ApiPaths.MANAGEMENT_ROOT) || 
               StringUtils.contains(request.getHeader(HttpHeaders.ACCEPT), MediaType.APPLICATION_JSON_VALUE);
    }
}
