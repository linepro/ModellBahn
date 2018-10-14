package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.keys.ZugConsistKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * ZugService. CRUD service for Zug
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.ZUG)
public class ZugService extends AbstractItemService<NameKey, Zug> {

    private IPersister<ZugConsist> consistPersister;

    public ZugService() {
        super(Zug.class);
        
        consistPersister = StaticPersisterFactory.get().createPersister(ZugConsist.class);
    }

    @JsonCreator
    public Zug create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.ZUG_TYP, required = false) String zugTypStr,
            @JsonProperty(value = ApiNames.NAMEN, required = false) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IZugTyp zugTyp = findZugTyp(zugTypStr, false);

        Zug entity = new Zug(id, name, bezeichnung, zugTyp, deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    public ZugConsist createZugConsist(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.ZUG, required = false) String zugStr,
            @JsonProperty(value = ApiNames.POSITION, required = false) Integer position,
            @JsonProperty(value = ApiNames.ARTIKEL, required = false) String artikelStr,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IZug zug = findZug(zugStr, true);
        IArtikel artikel = findArtikel(artikelStr, false);
        
        ZugConsist entity = new ZugConsist(id,  zug, position, artikel, deleted);

        debug("created: " + entity);

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
    public Response add(Zug entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Zug entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @POST
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response addConsist(@PathParam(ApiPaths.NAME_PARAM_NAME) String zugStr, @QueryParam(ApiPaths.ARTIKEL) String artikelId) {
        try {
            logPost(zugStr + "/" + artikelId);

            Zug zug = (Zug) findZug(zugStr, true);

            if (zug == null) {
                return getResponse(badRequest(null, "Zug " + zugStr + " does not exist"));
            }

            Artikel artikel = (Artikel) findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(null, "Artikel " + artikelId + " does not exist"));
            }

            ZugConsist zugConsist = new ZugConsist(null, zug, null, artikel, false);

            zug.addConsist(zugConsist);

            getPersister().update(zug);

            return getResponse(created(), zugConsist, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.ZUG_CONSIST_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response updateConsist(@PathParam(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathParam(ApiPaths.POSITION_PARAM_NAME) Integer position, @QueryParam(ApiPaths.ARTIKEL) String artikelId) {
        try {
            logPost(zugStr + "/" + position + "?" + artikelId);

            ZugConsist consist = (ZugConsist) findZugConsist(zugStr, position, true);

            if (consist == null) {
                return getResponse(badRequest(null, "ZugConsist " + zugStr + "/" + position + " does not exist"));
            }

            Artikel artikel = (Artikel) findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(null, "Artikel " + artikelId + " does not exist"));
            }

            consist.setArtikel(artikel);

            consist = getConsistPersister().update(consist);

            return getResponse(created(), consist, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.ZUG_CONSIST_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response deleteConsist(@PathParam(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathParam(ApiPaths.POSITION_PARAM_NAME) Integer position) {
        try {
            ZugConsist zugConsist = (ZugConsist) findZugConsist(zugStr, position, true);

            if (zugConsist == null) {
                return getResponse(badRequest(null, "ZugConsist " + zugStr + "/" + position + " does not exist"));
            }

            Zug zug = (Zug) zugConsist.getZug();

            zug.removeConsist(zugConsist);

            //getZugConsistPersister().delete(zugConsist);
            // TODO: resequence position for remaining items
            
            getPersister().update(zug);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    protected IZugConsist findZugConsist(String zugStr, Integer position, boolean eager) throws Exception {
        return findZugConsist(findZug(zugStr, eager), position, eager) ;
    }

    protected IZugConsist findZugConsist(IZug zug, Integer position, boolean eager) throws Exception {
        return getConsistPersister().findByKey(new ZugConsistKey(zug, position), eager);
    }

    protected IPersister<ZugConsist> getConsistPersister() {
        return consistPersister;
    }
}
