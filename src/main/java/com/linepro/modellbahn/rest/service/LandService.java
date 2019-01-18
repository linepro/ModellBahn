package com.linepro.modellbahn.rest.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.rest.json.serialization.WahrungDeserializer;
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
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.ILand;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.impl.Land;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * LandService.
 * CRUD service for Land
 * @author  $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.LAND)
@Path(ApiPaths.LAND)
public class LandService extends AbstractItemService<NameKey, ILand> {

    public LandService() {
        super(ILand.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static Land create() {
        return new Land();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static Land create(@JsonProperty(value = ApiNames.ID) Long id,
                    @JsonProperty(value = ApiNames.NAMEN) String name,
                    @JsonProperty(value = ApiNames.WAHRUNG)
                    @JsonDeserialize(using = WahrungDeserializer.class) IWahrung wahrung,
                    @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
                    @JsonProperty(value = ApiNames.DELETED) Boolean deleted) throws Exception {
        return new Land(id, name, bezeichnung, wahrung, deleted);
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Land by name", response = ILand.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Landen by example", response = ILand.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Land id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.WAHRUNG, value = "Land wahrung", example = "GBP", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Land code", example = "UK", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Land description", example = "Vereinigtes Königreich", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
})
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Land", response = ILand.class)
    public Response add(Land entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Land by name", response = ILand.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Land entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Land by name")
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
}
