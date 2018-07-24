package com.linepro.modellbahn.rest.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.rest.json.Views;

/**
 * ItemService.
 * Basic service with CRUD functionality
 * @author  $Author:$
 * @version $Id:$
 *
 * @param <E> the element type
 */
public class ItemService<E extends IItem> extends AbstractService<E> {

    /**
     * Instantiates a new item service.
     *
     * @param entityClass the entity class
     * @param persister the persister
     */
    public ItemService(final Class<E> entityClass, IPersister<E> persister) {
        super(entityClass, persister);
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the response
     */
    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam("id") Long id) {
        try {
            E entity = getPersister().findById(id);

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.ok().entity(entity));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Update.
     *
     * @param id the id
     * @param entity the entity
     * @return the response
     */
    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam("id") Long id, E entity) {
        try {
            entity.setId(id);
            
            E result = getPersister().update(entity);

            if (result == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.accepted().entity(result));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response
     */
    @DELETE
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam("id") Long id) {
        try {
            getPersister().delete(create(id));

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }
}