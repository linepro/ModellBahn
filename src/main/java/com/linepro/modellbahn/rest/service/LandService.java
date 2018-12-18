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
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.impl.Land;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * LandService.
 * CRUD service for Land
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.LAND)
public class LandService extends AbstractItemService<NameKey, Land> {

    private final IPersister<Wahrung> wahrungPersister;

    public LandService() {
        super(Land.class);

        wahrungPersister = StaticPersisterFactory.get().createPersister(Wahrung.class) ;
    }

    @JsonCreator
    public Land create(@JsonProperty(value=ApiNames.ID) Long id,
                    @JsonProperty(value=ApiPaths.NAME_PARAM_NAME) String name,
                    @JsonProperty(value=ApiNames.WAHRUNG) String wahrungStr,
                    @JsonProperty(value=ApiNames.BEZEICHNUNG) String bezeichnung,
                    @JsonProperty(value=ApiNames.DELETED) Boolean deleted) throws Exception {
        IWahrung wahrung = findWahrung(wahrungStr, false);
 
        Land entity = new Land(id, name, bezeichnung, wahrung, deleted);

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
    public Response add(Land entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Land entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
    
    protected IWahrung findWahrung(String wahrungStr) throws Exception {
        return getWahrungPersister().findByKey(new NameKey(wahrungStr), true);
    }
    
    private IPersister<Wahrung> getWahrungPersister() {
        return wahrungPersister;
    }
}
