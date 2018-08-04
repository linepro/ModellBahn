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
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.keys.NameKey;
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

    public ZugService() {
        super(Zug.class);
    }

    @JsonCreator
    public Zug create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.ZUG_TYP, required = false) String zugTypStr,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
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

    /**
     * Add a consist to the Zug
     * @param name
     * @param artikelId
     * @return
     */
    @POST
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response addConsist(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, @QueryParam(ApiPaths.ARTIKEL) String artikelId) {
        return getResponse(noContent());
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
}
