package com.linepro.modellbahn.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.KategorieDeserializer;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.KATEGORIE)
@Path(ApiPaths.KATEGORIE)
public class KategorieService extends AbstractItemService<NameKey,  IKategorie> {

    private final IPersister<IUnterKategorie> unterKategoriePersister;

    public KategorieService() {
        super(IKategorie.class);

        unterKategoriePersister = StaticPersisterFactory.get().createPersister(IUnterKategorie.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static Kategorie Kategorie() {
        return new Kategorie();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static Kategorie create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return  new Kategorie(id, name, bezeichnung, deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static UnterKategorie createUnterKategorie() {
        return new UnterKategorie();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static UnterKategorie createUnterKategorie(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.KATEGORIE)
            @JsonDeserialize(using = KategorieDeserializer.class) IKategorie kategorie,
            @JsonProperty(value = ApiNames.NAMEN) String name,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new UnterKategorie(id, kategorie, name, bezeichnung, deleted);
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Kategorie by name", response = IKategorie.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Kategorieen by example", response = IKategorie.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Kategorie id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Kategorie code", example = "LOKOMOTIV", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Kategorie description", example = "Lokomotiv", dataType = "String", paramType = "query"),
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
    @ApiOperation(code = 201, value = "Adds an Kategorie", response = IKategorie.class)
    public Response add(Kategorie entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Kategorie by name", response = IKategorie.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Kategorie entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Kategorie by name")
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @GET
    @Path(ApiNames.UNTER_KATEGORIEN)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds UnterKategorieen by kategorie", response = IUnterKategorie.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam( name = ApiNames.KATEGORIEN, value = "List of Kategorie names", dataType = "[Ljava.lang.String;", paramType = "query"),
            @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response searchUnterKategorie(@Context UriInfo uriInfo, @QueryParam(ApiNames.KATEGORIEN) final List<String> kategorien) {
        try {
            logGet(String.format(ApiPaths.UNTER_KATEGORIEN_LOG, getEntityClassName()) + ": " + kategorien);

            MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

            IUnterKategorie template = new UnterKategorie();

            Map<String,List<String>> references = new HashMap<>(1);

            if (kategorien != null && !kategorien.isEmpty()) {
                references.put(DBNames.KATEGORIE, kategorien);
            }

            Integer pageNumber = null;
            Integer pageSize = null;
            Integer maxPage = null;

            String pageNumberStr = queryParameters.getFirst(ApiNames.PAGE_NUMBER);
            String pageSizeStr   = queryParameters.getFirst(ApiNames.PAGE_SIZE);

            if (pageNumberStr != null || pageSizeStr != null) {
                pageNumber = NumberUtils.toInt(pageNumberStr, FIRST_PAGE);
                pageSize   = NumberUtils.toInt(pageSizeStr, DEFAULT_PAGE_SIZE);

                Long rowCount = getUnterKategoriePersister().countAll(template, references);

                maxPage = new Double(Math.floor(rowCount.doubleValue() / pageSize.doubleValue())).intValue();
            }

            List<IUnterKategorie> entities = new ArrayList<>(getUnterKategoriePersister().findAll(template, references, pageNumber, pageSize));

            if (entities.isEmpty()) {
                return getResponse(noContent());
            }

            List<Link> navigation = getNavLinks(uriInfo, pageNumber, pageSize, maxPage);

            return getResponse(ok(), new ArrayList<>(entities), true, navigation);
        } catch (Exception e) {
            return getResponse(e);
        }
    }
    
    @POST
    @Path(ApiPaths.UNTER_KATEGORIE_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Adds an UnterKategorie to a kategorie", response = IUnterKategorie.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response addUnterKategorie(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorie newUnterKategorie) {
        try {
            logPost(String.format(ApiPaths.UNTER_KATEGORIE_ROOT_LOG, getEntityClassName(), kategorieStr) + ": " + newUnterKategorie);

            IKategorie kategorie = findKategorie(kategorieStr, true);

            if (kategorie == null) {
                return getResponse(badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr)));
            }

            newUnterKategorie.setDeleted(false);

            kategorie.addUnterKategorie(newUnterKategorie);

            getPersister().update(kategorie);

            return getResponse(created(), newUnterKategorie, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(code = 202, value = "Updates an UnterKategorie by kategorie and name", response = IUnterKategorie.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response updateUnterKategorie(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, UnterKategorie newUnterKategorie) {
        try {
            logPut(String.format(ApiPaths.UNTER_KATEGORIE_LOG, getEntityClassName(), kategorieStr, unterKategorieStr) + ": " + newUnterKategorie);

            IKategorie kategorie = findKategorie(kategorieStr, false);

            if (kategorie == null) {
                return getResponse(badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr)));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorieStr, unterKategorieStr, false);

            if (unterKategorie == null) {
                return getResponse(badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr)));
            } else if (newUnterKategorie.getKategorie() == null) {
                newUnterKategorie.setKategorie(kategorie);
            } else if (!newUnterKategorie.getKategorie().equals(kategorie)) {
                // Attempt to change kategorie not allowed
                return getResponse(badRequest(ApiMessages.UNTERKATEGORIE_KATEGORIE_FIXED));
            }

            unterKategorie = getUnterKategoriePersister().merge(unterKategorie.getId(), newUnterKategorie);

            return getResponse(accepted(), unterKategorie, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.UNTER_KATEGORIE_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(code = 204, value = "Deletes an UnterKategorie by kategorie and name")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 402, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response deleteUnterKategorie(@PathParam(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathParam(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        try {
            logDelete(String.format(ApiPaths.UNTER_KATEGORIE_LOG, getEntityClassName(), kategorieStr, unterKategorieStr));

            IKategorie kategorie = findKategorie(kategorieStr, true);

            if (kategorie == null) {
                return getResponse(badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr)));
            }

            IUnterKategorie unterKategorie = findUnterKategorie(kategorie, unterKategorieStr, true);

            if (unterKategorie == null) {
                return getResponse(badRequest(getMessage(ApiMessages.UNTER_KATEGORIE_DOES_NOT_EXIST, kategorieStr, unterKategorieStr)));
            }

            kategorie.removeUnterKategorie(unterKategorie);

            getPersister().update(kategorie);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private IPersister<IUnterKategorie> getUnterKategoriePersister() {
        return unterKategoriePersister;
    }
}
