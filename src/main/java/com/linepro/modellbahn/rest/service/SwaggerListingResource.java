package com.linepro.modellbahn.rest.service;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.listing.ApiListingResource;

@Path(ApiPaths.SWAGGER_RESOURCE)
public class SwaggerListingResource extends ApiListingResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, "application/yaml"})
    @ApiOperation(value = "The swagger definition in either JSON or YAML", hidden = true)
    public Response getListing(
            @Context Application app,
            @Context ServletConfig sc,
            @Context HttpHeaders headers,
            @Context UriInfo uriInfo,
            @PathParam("type") String type) {
        return super.getListing(app, sc, headers, uriInfo, type);
    }        
}