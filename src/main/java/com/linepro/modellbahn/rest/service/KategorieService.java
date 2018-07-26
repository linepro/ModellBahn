package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * KategorieService.
 * CRUD service for Kategorie
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.KATEGORIE)
public class KategorieService extends NamedItemService<Kategorie> {

    /** The unter kategorie persister. */
    private final IPersister<UnterKategorie> unterKategoriePersister;

    /**
     * Instantiates a new kategorie service.
     */
    public KategorieService() {
        super(Kategorie.class);

        unterKategoriePersister = StaticPersisterFactory.get().createNamedItemPersister(UnterKategorie.class);
    }

    /**
     * Creates the.
     *
     * @param id the id
     * @param kategorieStr the kategorie str
     * @param name the name
     * @param bezeichnung the bezeichnung
     * @param deleted the deleted
     * @return the unter kategorie
     * @throws Exception the exception
     */
    @JsonCreator
    public UnterKategorie create(@JsonProperty(value=ApiNames.ID, required=false) Long id, 
                    @JsonProperty(value=ApiPaths.KATEGORIE_PARAM_NAME, required=false) String kategorieStr, 
                    @JsonProperty(value=ApiNames.NAME, required=false) String name, 
                    @JsonProperty(value=ApiNames.DESCRIPTION, required=false) String bezeichnung, 
                    @JsonProperty(value=ApiNames.DELETED, required=false) Boolean deleted) throws Exception {
        Kategorie kat = findKategorie(kategorieStr);

        UnterKategorie entity = new UnterKategorie(id, kat, name, bezeichnung, deleted);

        return entity;
    }

    /**
     * Find kategorie.
     *
     * @param kategorieStr the kategorie str
     * @return the kategorie
     * @throws Exception the exception
     */
    protected Kategorie findKategorie(String kategorieStr) throws Exception {
        return ((IPersister<Kategorie>) getPersister()).findByKey(new Kategorie(kategorieStr), false);
    }

    protected UnterKategorie findUnterKategorie(String kategorieStr, String name) throws Exception {
        Kategorie kategorie = findKategorie(kategorieStr);

        return getUnterKategoriePersister().findByKey(new UnterKategorie(kategorie, name), false);
    }

    /**
     * Gets the.
     *
     * @param kategorieStr the kategorie
     * @param name the name
     * @return the response
     * @throws Exception 
     */
    @GET
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String name) {
        try {
            UnterKategorie entity = findUnterKategorie(kategorieStr, name);

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.ok().entity(entity.addLinks(getUriInfo(), true, true)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Adds the.
     *
     * @param kategorieStr the kategorie
     * @param unterkategorie the unterkategorie
     * @return the response
     * @throws Exception the exception
     */
    @POST
    @Path(ApiPaths.KATEGORIE_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorie unterkategorie) throws Exception {
        Kategorie kategorie = findKategorie(kategorieStr);
        
        return getResponse(null);
    }

    /**
     * Update.
     *
     * @param kategorieStr the kategorie
     * @param name the name
     * @param entity the entity
     * @return the response
     */
    @PUT
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String name, UnterKategorie entity) throws Exception {
        Kategorie kategorie = findKategorie(kategorieStr);
        
        return getResponse(null);
    }

    /**
     * Delete.
     *
     * @param kategorieStr the kategorie
     * @param name the name
     * @return the response
     * @throws Exception 
     */
    @DELETE
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String name) throws Exception {
        try {
            Kategorie entity = findKategorie(kategorieStr);

            if (entity == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    protected IPersister<UnterKategorie> getUnterKategoriePersister() {
        return unterKategoriePersister;
    }
}
