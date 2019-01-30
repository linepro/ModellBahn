package com.linepro.modellbahn.jersey;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

/**
 * SecurityRequestFilter.
 * Null security filter.
 * TODO: create a proper one.
 * @author   $Author$
 * @version  $Id$
 */
@PreMatching
@Priority(Integer.MAX_VALUE)
public class SecurityRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
    }
}
