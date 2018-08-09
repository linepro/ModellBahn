package com.linepro.modellbahn.jersey;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linepro.modellbahn.util.ToStringBuilder;

//@ConstrainedTo(RuntimeType.SERVER)
@PreMatching
@Priority(Integer.MAX_VALUE)
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    protected static final AtomicInteger eventId = new AtomicInteger(1);
    
    protected static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class.getName());

    protected static final String EVENT_ID = "com.linepro.modellbahn.jersey.LoggingFiler.eventId";
    
    /**
     * Create a logging filter.
     */
    public LoggingFilter() {
    }

    @Override
    public void filter(final ContainerRequestContext context) throws IOException {
        final long id = eventId.incrementAndGet();

        context.setProperty(EVENT_ID, id);

        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug(format(id, context));
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
        if (!logger.isDebugEnabled()) {
            return;
        }

        final Object requestId = requestContext.getProperty(EVENT_ID);

        logger.debug(format(requestId, responseContext));
   }

    private String format(Object id, ContainerRequestContext context) {
        return new ToStringBuilder(context, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("Request")
                .append("eventId", id)
                .append("uri", context.getUriInfo().getAbsolutePath().toString())
                .append("method", context.getMethod())
                .append("parameters", context.getUriInfo().getQueryParameters())
                .toString();
    }

    private String format(Object id, ContainerResponseContext context) {
        return new ToStringBuilder(context, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("Response")
                .append("eventId", id)
                .append("entity", context.getEntity())
                .append("status", context.getStatus())
                .toString();
    }
}