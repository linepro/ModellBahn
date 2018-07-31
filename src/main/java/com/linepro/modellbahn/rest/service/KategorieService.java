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
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.KATEGORIE)
public class KategorieService extends AbstractItemService<NameKey,  Kategorie> {

    private final IPersister<UnterKategorie> unterKategoriePersister;

    public KategorieService() {
        super(Kategorie.class);

        unterKategoriePersister = StaticPersisterFactory.get().createPersister(UnterKategorie.class) ;
    }

    @JsonCreator
    public Kategorie create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Kategorie entity = create();

        entity.setId(id);
        entity.setName(name);
        entity.setBezeichnung(bezeichnung);
        entity.setDeleted(deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    public UnterKategorie create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.KATEGORIE, required = false) String kategorieStr,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Kategorie kategorie = findKategorie(kategorieStr, false);

        UnterKategorie entity = new UnterKategorie(id, kategorie, name, bezeichnung, deleted);

        return entity;
    }

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
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(Kategorie entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Kategorie entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @GET
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        try {
            Kategorie kategorie = findKategorie(kategorieStr, true);

            if (kategorie == null) {
                return getResponse(badRequest(null, "Kategorie " + kategorieStr + " does not exist"));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorieStr, unterKategorieStr);

            if (unterKategorie != null) {
                return getResponse(ok(), unterKategorie, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @POST
    @Path(ApiPaths.KATEGORIE_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorie unterKategorie)
            throws Exception {
        try {
            logPost(kategorieStr + "/" + unterKategorie);

            Kategorie kategorie = findKategorie(kategorieStr, true);

            if (kategorie == null) {
                return getResponse(badRequest(null, "Kategorie " + kategorieStr + " does not exist"));
            }

            kategorie.addUnterKategorie(unterKategorie);

            getPersister().update(kategorie);

            return getResponse(created(), unterKategorie, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, UnterKategorie newUnterKategorie)
            throws Exception {
        try {
            logPut(kategorieStr + "/" + unterKategorieStr + ": " + newUnterKategorie);

            Kategorie kategorie = findKategorie(kategorieStr, false);

            if (kategorie == null) {
                return getResponse(badRequest(null, "Kategorie " + kategorieStr + " does not exist"));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorieStr, unterKategorieStr);

            if (unterKategorie == null) {
                return getResponse(badRequest(null, "Kategorie " + kategorieStr + " does not exist"));
            } else if (newUnterKategorie.getKategorie() == null) {
                newUnterKategorie.setKategorie(kategorie);
            } else if (!newUnterKategorie.getKategorie().equals(kategorie)) {
                // Attempt to change kategorie not allowed
                return getResponse(badRequest(null, "You cannot change the kategorie for an unterkategorie, create a new one"));
            }

            unterKategorie = getUnterKategoriePersister().update(newUnterKategorie);

            return getResponse(accepted(), unterKategorie, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) throws Exception {
        try {
            UnterKategorie unterKategorie = (UnterKategorie) findUnterKategorie(kategorieStr, unterKategorieStr);

            if (unterKategorie == null) {
                return getResponse(badRequest(null, "UnterKategorie " + kategorieStr + "/" + unterKategorieStr + " does not exist"));
            }

            Kategorie kategorie = (Kategorie) unterKategorie.getKategorie();

            kategorie.removeUnterKategorie(unterKategorie);

            //getUnterKategoriePersister().delete(unterKategorie);

            getPersister().update(kategorie);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    protected Kategorie findKategorie(String kategorieStr, boolean eager) throws Exception {
        return getPersister().findByKey(kategorieStr, eager);
    }

    protected IUnterKategorie findUnterKategorie(String kategorieStr, String unterKategorieStr) throws Exception {
        return getUnterKategoriePersister().findByKey(new UnterKategorieKey(findKategorie(kategorieStr, false), unterKategorieStr), true);
    }
    
    protected IUnterKategorie findUnterKategorie(IKategorie kategorie, String unterKategorieStr) throws Exception {
        return getUnterKategoriePersister().findByKey(new UnterKategorieKey(kategorie, unterKategorieStr), true);
    }

    protected IPersister<UnterKategorie> getUnterKategoriePersister() {
        return unterKategoriePersister;
    }
}
