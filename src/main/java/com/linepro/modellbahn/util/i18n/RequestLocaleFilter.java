package com.linepro.modellbahn.util.i18n;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@PreMatching
@Priority(Integer.MAX_VALUE)
public class RequestLocaleFilter implements ContainerRequestFilter {

  @Context
  private HttpHeaders headers;

  @Override
  public void filter(ContainerRequestContext requestContext) {
    ThreadLocale.set(headers.getAcceptableLanguages().get(0));
  }
}