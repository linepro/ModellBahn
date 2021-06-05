package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "AccessDeniedHandler")
public class ModellBahnAccessDeniedHandler extends AccessDeniedHandlerImpl {

    private final JsonAccessDeniedHandler handler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        if (!handler.isDenied(request, response, exception)) {
            super.handle(request, response, exception);
        }
    }
}
