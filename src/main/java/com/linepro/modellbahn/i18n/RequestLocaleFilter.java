package com.linepro.modellbahn.i18n;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@PreMatching
@Priority(Integer.MAX_VALUE)
@Component(PREFIX + "RequestLocaleFilter")
public class RequestLocaleFilter implements ContainerRequestFilter {

  @Context
  private HttpHeaders headers;

  @Override
  public void filter(ContainerRequestContext requestContext) {
      LocaleContextHolder.setLocale(headers.getAcceptableLanguages().get(0));
  }
}