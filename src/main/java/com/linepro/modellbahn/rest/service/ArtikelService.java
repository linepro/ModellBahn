package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.keys.IdKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.ARTIKEL)
public class ArtikelService extends AbstractItemService<IdKey, Artikel> {

    /**
     * Instantiates a new decoder typ service.
     */
    public ArtikelService() {
        super(Artikel.class);
    }

    /**
     * Creates the.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     * @return the e
     * @throws Exception
     *             the exception
     */
    @JsonCreator
    public Artikel create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.ID_PARAM_NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Artikel entity = create();

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
     * @param name
     *            the name
     * @return the response
     */
    @GET
    @Path(ApiPaths.ID_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.ID_PARAM_NAME) Long name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    /**
     * Update.
     *
     * @param name
     *            the name
     * @param entity
     *            the entity
     * @return the response
     */
    @PUT
    @Path(ApiPaths.ID_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.ID_PARAM_NAME) Long name, Artikel entity) {
        return super.update(name, entity);
    }

    /**
     * Delete.
     *
     * @param name
     *            the name
     * @return the response
     */
    @DELETE
    @Path(ApiPaths.ID_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.ID_PARAM_NAME) Long name) {
        return super.delete(name);
    }
}
