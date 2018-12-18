package com.linepro.modellbahn.jersey;

import com.linepro.modellbahn.util.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import java.util.concurrent.atomic.AtomicInteger;

//@ConstrainedTo(RuntimeType.SERVER)
@PreMatching
@Priority(Integer.MAX_VALUE)
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final AtomicInteger eventId = new AtomicInteger(1);
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class.getName());

    private static final String EVENT_ID = "com.linepro.modellbahn.jersey.LoggingFiler.eventId";
    
    /**
     * Create a logging filter.
     */
    public LoggingFilter() {
    }

    @Override
    public void filter(final ContainerRequestContext context) {
        final long id = eventId.incrementAndGet();

        context.setProperty(EVENT_ID, id);

        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug(format(id, context));
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        final Object requestId = requestContext.getProperty(EVENT_ID);

        logger.debug(format(requestId, responseContext));
   }

    private String format(Object id, ContainerRequestContext context) {
        return new ToStringBuilder(context)
                .append("Request")
                .append("eventId", id)
                .append("uri", context.getUriInfo().getAbsolutePath().toString())
                .append("method", context.getMethod())
                .append("parameters", context.getUriInfo().getQueryParameters())
                .toString();
    }

    private String format(Object id, ContainerResponseContext context) {
        return new ToStringBuilder(context)
                .append("Response")
                .append("eventId", id)
                .append("entity", context.getEntity())
                .append("status", context.getStatus())
                .toString();
    }
}