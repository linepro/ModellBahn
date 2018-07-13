package com.linepro.modellbahn.rest.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.ConvertUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.guice.StaticPersisterFactory;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IItemPersister;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

public abstract class ItemService<E extends IItem> {


    @Context
    protected UriInfo uriInfo;

    protected final IItemPersister<E> persister;

    protected final Class<E> entityClass;

    protected final Map<String,Selector> selectors;
    
    public ItemService(final Class<E> entityClass) {
        this.persister = StaticPersisterFactory.create(entityClass);
        this.entityClass = entityClass;
        this.selectors = new SelectorsBuilder().build(entityClass, Arrays.asList(JsonGetter.class));
    }

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        try {
            E entity = persister.find(id);

            if (entity == null) {
                return getResponse(Response.noContent());
            }

            return getResponse(Response.ok().entity(entity));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@Context UriInfo info) {
        try {
            E template = getTemplate(info.getQueryParameters());

            List<E> entities = persister.findAll(template);

            if (entities.isEmpty()) {
                return getResponse(Response.noContent());
            }

            return getResponse(Response.ok(entities));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(E entity) {
        try {
            E result = persister.add(entity);

            return getResponse(Response.ok().entity(result));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @PUT
    @Path("/{id: [0-9]+}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(E entity) {
        try {
            E result = persister.update(entity);

            return getResponse(Response.accepted().entity(result));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @DELETE
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            persister.delete(id);

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    protected E getTemplate(MultivaluedMap<String, String> queryParameters) throws Exception {
        E template = entityClass.newInstance();

        if (!queryParameters.isEmpty()) {
            for (String name : queryParameters.keySet()) {
                Object value = queryParameters.getFirst(name);

                Selector selector = selectors.get(name);
                
                if (selector != null) {
                    selector.getSetter().invoke(template, ConvertUtils.convert(value, selector.getGetter().getReturnType()));
                }
            }
        }

        return template;
    }

    protected Response serverError(Exception e) {
        return getResponse(Response.serverError());
    }

    protected Response getResponse(ResponseBuilder builder) {
        return builder.link(uriInfo.getAbsolutePath(), "home").build();
    }
}