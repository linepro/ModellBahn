package com.linepro.modellbahn.rest.service;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * KategorieService. CRUD service for Kategorie
 * Transpires that two way ManyToOne are best updated from the parent end 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.KATEGORIE)
public class KategorieService extends AbstractItemService<String, Kategorie> {

    /**
     * Instantiates a new kategorie service.
     */
    public KategorieService() {
        super(Kategorie.class);
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
    public Kategorie create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.NAME_PARAM_NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Kategorie entity = create();

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
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
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
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Kategorie entity) {
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
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    /**
     * Creates the.
     *
     * @param id
     *            the id
     * @param kategorieStr
     *            the kategorie str
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     * @return the unter kategorie
     * @throws Exception
     *             the exception
     */
    @JsonCreator
    public UnterKategorie create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.KATEGORIE_PARAM_NAME, required = false) String kategorieStr,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Kategorie kategorie = findKategorie(kategorieStr);

        UnterKategorie entity = new UnterKategorie(id, kategorie, name, bezeichnung, deleted);

        return entity;
    }

    /**
     * Find kategorie.
     *
     * @param kategorieStr
     *            the kategorie str
     * @return the kategorie
     * @throws Exception
     *             the exception
     */
    protected Kategorie findKategorie(String kategorieStr) throws Exception {
        return getPersister().findByKey(kategorieStr, true);
    }

    /**
     * Gets the.
     *
     * @param kategorieStr
     *            the kategorie
     * @param name
     *            the name
     * @return the response
     * @throws Exception
     */
    @GET
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        try {
            Kategorie kategorie = findKategorie(kategorieStr);

            if (kategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorie, unterKategorieStr);

            if (unterKategorie != null) {
                return getResponse(Response.ok().entity(unterKategorie.addLinks(getUriInfo(), true, true)));
            }

            return getResponse(Response.status(Status.NOT_FOUND));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Adds the.
     *
     * @param kategorieStr
     *            the kategorie
     * @param unterkategorie
     *            the unterKategorie
     * @return the response
     * @throws Exception
     *             the exception
     */
    @POST
    @Path(ApiPaths.KATEGORIE_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorie unterKategorie)
            throws Exception {
        try {
            logger.info("POST " + kategorieStr + "/" + unterKategorie);

            Kategorie kategorie = findKategorie(kategorieStr);

            if (kategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            kategorie.addUnterKategorie(unterKategorie);

            getPersister().update(kategorie);

            return getResponse(Response.ok().entity(unterKategorie.addLinks(getUriInfo(), true, true)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Update.
     *
     * @param kategorieStr
     *            the kategorie
     * @param name
     *            the name
     * @param newUnterKategorie
     *            the entity
     * @return the response
     */
    @PUT
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, UnterKategorie newUnterKategorie)
            throws Exception {
        try {
            info("PUT " + kategorieStr + "/" + unterKategorieStr + ": " + newUnterKategorie);

            // You are not permitted to update the kategorie of an
            // unterkategorie : you must do add new delete old
            Kategorie kategorie = findKategorie(kategorieStr);

            if (kategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorie, unterKategorieStr);

            if (unterKategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            if (newUnterKategorie.getBezeichnung() != null) {
                unterKategorie.setBezeichnung(newUnterKategorie.getBezeichnung());
            }
            
            if (newUnterKategorie.getDeleted() != null) {
                unterKategorie.setDeleted(newUnterKategorie.getDeleted());
            }

            getPersister().update(kategorie);

            return getResponse(Response.accepted().entity(newUnterKategorie.addLinks(getUriInfo(), true, true)));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    /**
     * Delete.
     *
     * @param kategorieStr
     *            the kategorie
     * @param name
     *            the name
     * @return the response
     * @throws Exception
     */
    @DELETE
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) throws Exception {
        try {
            Kategorie kategorie = findKategorie(kategorieStr);

            if (kategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorie, unterKategorieStr);

            if (unterKategorie == null) {
                return getResponse(Response.status(Status.NOT_FOUND));
            }

            kategorie.removeUnterKategorie(unterKategorie);

            getPersister().update(kategorie);

            return getResponse(Response.noContent());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    protected IUnterKategorie findUnterKategorie(Kategorie kategorie, String unterKategorieStr) {
        for (IUnterKategorie unterKategorie : kategorie.getUnterKategorien()) {
            if (unterKategorieStr.equals(unterKategorie.getName())) {
                return unterKategorie;
            }
        }
        return null;
    }
}
