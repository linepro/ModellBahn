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
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * WahrungService. CRUD service for Wahrung
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.WAHRUNG, description = "Wahrung maintenance")
@Path(ApiPaths.WAHRUNG)
public class WahrungService extends AbstractItemService<NameKey, Wahrung> {

    public WahrungService() {
        super(Wahrung.class);
    }

    @JsonCreator
    public Wahrung create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.DECIMALS) Integer decimals,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        Wahrung entity = new Wahrung(id, name, bezeichnung, decimals, deleted);
        
        debug("created: " + entity);

        return entity;
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Wahrung by name", response = Wahrung.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Wahrungen by example", response = Wahrung.class, responseContainer = "List")
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Updates a Wahrung by name", response = Wahrung.class)
    public Response add(Wahrung entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Updates a Wahrung by name", response = Wahrung.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Wahrung entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes a Wahrung by name")
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
}
