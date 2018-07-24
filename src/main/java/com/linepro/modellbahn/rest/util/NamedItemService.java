package com.linepro.modellbahn.rest.util;

import static javax.ws.rs.HttpMethod.GET;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;

/**
 * NamedItemService.
 * Basic CRUD for NamedItems
 * @author  $Author:$
 * @version $Id:$
 *
 * @param <E> the element type
 */
public abstract class NamedItemService<E extends INamedItem> extends AbstractService<E> {

    /**
     * Instantiates a new named item service.
     *
     * @param entityClass the entity class
     */
    public NamedItemService(final Class<E> entityClass) {
        super(entityClass, StaticPersisterFactory.get().createNamedItemPersister(entityClass));
    }

    /**
     * Creates the.
     *
     * @param id the id
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param deleted the deleted
     * @return the e
     * @throws Exception the exception
     */
    @JsonCreator
    public E create(@JsonProperty(value="id", required=false) Long id, 
                    @JsonProperty(value="name", required=false) String name, 
                    @JsonProperty(value="description", required=false) String bezeichnung, 
                    @JsonProperty(value="deleted", required=false) Boolean deleted) throws Exception {
        E entity = create();

        entity.setId(id);
        entity.setName(name);
        entity.setBezeichnung(bezeichnung);
        entity.setDeleted(deleted);

        return entity;
    }

    /**
     * Gets the.
     *
     * @param name the name
     * @return the response
     */
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam("name") String name) {
        try {
            E entity = getPersister().findByKey(create(name));

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.ok().entity(getRepresentation(entity)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Update.
     *
     * @param name the name
     * @param entity the entity
     * @return the response
     */
    @PUT
    @Path("/{name}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam("name") String name, E entity) {
        try {
            entity.setName(name);

            E result = getPersister().update(entity);

            return getResponse(Response.accepted().entity(getRepresentation(result)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Delete.
     *
     * @param name the name
     * @return the response
     */
    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam("name") String name) {
        try {
            getPersister().delete(create(name));

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Creates the.
     *
     * @param name the name
     * @return the e
     * @throws Exception the exception
     */
    protected E create(String name) throws Exception {
        E template = getEntityClass().newInstance();
        template.setName(name);
        return template;
    }

    /**
     * Adds the self.
     *
     * @param item the item
     */
    protected void addSelf(INamedItem item) {
        item.addLink(makeLink(uriInfo.getAbsolutePath(), item.getName(), "self", GET));
    }

}