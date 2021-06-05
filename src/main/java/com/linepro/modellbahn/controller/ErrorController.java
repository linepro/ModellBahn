package com.linepro.modellbahn.controller;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    public static final String ERROR = "/error";

    private static final Map<Integer,String> ERRORS = Stream.of(
                      new SimpleImmutableEntry<>(HttpStatus.BAD_REQUEST.value(), "error/400"),
                      new SimpleImmutableEntry<>(HttpStatus.UNAUTHORIZED.value(), "error/401"),
                      new SimpleImmutableEntry<>(HttpStatus.FORBIDDEN.value(), "error/403"),
                      new SimpleImmutableEntry<>(HttpStatus.NOT_FOUND.value(), "error/404"),
                      new SimpleImmutableEntry<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error/500")
                    )
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

    @GetMapping(path = ERROR, produces = MediaType.TEXT_HTML)
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Optional.ofNullable(ERRORS.get(getStatusCode(request)))
                        .orElse("error"));
        //modelAndView.addObject("",object);
        //modelAndView.setStatus(null);
        return modelAndView;
    }

    protected Integer getStatusCode(HttpServletRequest request) {
        try {
            return (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public String getErrorPath() {
        return ERROR;
    }
}