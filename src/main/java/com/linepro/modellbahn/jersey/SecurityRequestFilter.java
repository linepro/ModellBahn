package com.linepro.modellbahn.jersey;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 * SecurityRequestFilter.
 * Null security filter.
 * TODO: create a proper one.
 * @author   $Author$
 * @version  $Id$
 */
public class SecurityRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    }
}
