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
public abstract class NamedItemService<E extends INamedItem> extends AbstractItemService<E> {

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
    public E create(@JsonProperty(value=ApiNames.ID, required=false) Long id, 
                    @JsonProperty(value=ApiPaths.NAME_PARAM_NAME, required=false) String name, 
                    @JsonProperty(value=ApiNames.DESCRIPTION, required=false) String bezeichnung, 
                    @JsonProperty(value=ApiNames.DELETED, required=false) Boolean deleted) throws Exception {
        E entity = create();

        entity.setId(id);
        entity.setName(name);
        entity.setBezeichnung(bezeichnung);
        entity.setDeleted(deleted);

        info("create " + entity);

        return entity;
    }

    /**
     * Gets the.
     *
     * @param name the name
     * @return the response
     */
    @GET
    @Path(ApiPaths.NAME_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        try {
            info("GET " + name);

            E entity = getPersister().findByKey(create(name), true);

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.ok().entity(entity.addLinks(getUriInfo(), true, true)));
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
    @Path(ApiPaths.NAME_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, E entity) {
        try {
            info("PUT " + name + ": " + entity);

            entity.setName(name);

            E result = getPersister().update(entity);

            return getResponse(Response.accepted().entity(result.addLinks(getUriInfo(), true, true)));
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
    @Path(ApiPaths.NAME_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        try {
            info("DELETE " + name);

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
}