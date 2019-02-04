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
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.ArtikelDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ZugDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ZugTypDeserializer;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ZugService. CRUD service for Zug
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ZUG)
@Path(ApiPaths.ZUG)
public class ZugService extends AbstractItemService<NameKey, IZug> {

    private final IPersister<IZugConsist> consistPersister;

    public ZugService() {
        super(IZug.class);

        consistPersister = StaticPersisterFactory.get().createPersister(IZugConsist.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static Zug create() {
        return new Zug();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static Zug create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.ZUG_TYP)
            @JsonDeserialize(using = ZugTypDeserializer.class) IZugTyp zugTyp,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new Zug(id, name, bezeichnung, zugTyp, deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ZugConsist createZugConsist() {
        return new ZugConsist();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static ZugConsist createZugConsist(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.ZUG)
            @JsonDeserialize(using = ZugDeserializer.class) IZug zug,
            @JsonProperty(value = ApiNames.POSITION) Integer position,
            @JsonProperty(value = ApiNames.ARTIKEL)
            @JsonDeserialize(using = ArtikelDeserializer.class) IArtikel artikel,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new ZugConsist(id,  zug, position, artikel, deleted);
    }

    @Override
    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a IZug by name", response = IZug.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Zugen by example", response = IZug.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Zug id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ZUG_TYP, value = "Zug typ", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Zug code", example = "BAVARIA", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Zug description", example = "TEE „Bavaria“", dataType = "String", paramType = "query"),
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
    @ApiOperation(code = 201, value = "Adds a Zug", response = IZug.class)
    public Response add(Zug entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Zug by name", response = IZug.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Zug entity) {
        return super.update(name, entity);
    }

    @Override
    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Zug by name")
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @POST
    @Path(ApiPaths.ZUG_CONSIST_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a vehicle to the end of a named Zug", response = IZugConsist.class)
    public Response addConsist(@PathParam(ApiPaths.ZUG_PARAM_NAME) String zugStr, @QueryParam(ApiPaths.ARTIKEL) String artikelId) {
        try {
            logPost(String.format(ApiPaths.ZUG_CONSIST_ROOT_LOG, getEntityClassName(), zugStr) + ": " + artikelId);

            IZug zug = findZug(zugStr, true);

            if (zug == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ZUG_DOES_NOT_EXIST, zugStr)));
            }

            IArtikel artikel = findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId)));
            }

            IZugConsist zugConsist = new ZugConsist(null, zug, zug.getConsist().size()+1, artikel, false);

            zug.addConsist(zugConsist);

            getPersister().update(zug);

            return getResponse(created(), zugConsist, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.ZUG_CONSIST_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a vehicle in a named Zug", response = IZugConsist.class)
    public Response updateConsist(@PathParam(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathParam(ApiPaths.POSITION_PARAM_NAME) Integer position, @QueryParam(ApiPaths.ARTIKEL) String artikelId) {
        try {
            logPut(String.format(ApiPaths.ZUG_CONSIST_LOG, getEntityClassName(), zugStr, position) + ": " + artikelId);

            IZugConsist consist = findZugConsist(zugStr, position, true);

            if (consist == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ZUG_CONSIST_DOES_NOT_EXIST, zugStr, position)));
            }

            IArtikel artikel = findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId)));
            }

            consist.setArtikel(artikel);

            consist = getConsistPersister().update(consist);

            return getResponse(created(), consist, true, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.ZUG_CONSIST_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Removes a vehicle from a named Zug")
    public Response deleteConsist(@PathParam(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathParam(ApiPaths.POSITION_PARAM_NAME) Integer position) {
        try {
            logDelete(String.format(ApiPaths.ZUG_CONSIST_LOG, getEntityClassName(), zugStr, position));
        
            IZugConsist zugConsist = findZugConsist(zugStr, position, true);

            if (zugConsist == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ZUG_CONSIST_DOES_NOT_EXIST, zugStr, position)));
            }

            IZug zug = zugConsist.getZug();

            zug.removeConsist(zugConsist);

            // TODO: resequence position for remaining items
            
            getPersister().update(zug);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private IPersister<IZugConsist> getConsistPersister() {
        return consistPersister;
    }
}
