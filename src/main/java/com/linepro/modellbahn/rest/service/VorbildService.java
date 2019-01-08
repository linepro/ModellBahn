package com.linepro.modellbahn.rest.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.impl.Vorbild;
import com.linepro.modellbahn.model.keys.VorbildKey;
import com.linepro.modellbahn.model.util.LeistungsUbertragung;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.AcceptableMediaTypes;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.FileUploadHandler;
import com.linepro.modellbahn.rest.util.IFileUploadHandler;
import com.linepro.modellbahn.util.StaticContentFinder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * VorbildService. CRUD service for Vorbild
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.VORBILD, description = "Vorbild maintenance")
@Path(ApiPaths.VORBILD)
public class VorbildService extends AbstractItemService<VorbildKey, Vorbild> {

    public VorbildService() {
        super(Vorbild.class);
    }

    @JsonCreator
    public Vorbild create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.GATTUNG) IGattung gattung,
            @JsonProperty(value = ApiNames.UNTER_KATEGORIE, required=true) IUnterKategorie unterKategorie,
            @JsonProperty(value = ApiNames.BAHNVERWALTUNG) IBahnverwaltung bahnverwaltung,
            @JsonProperty(value = ApiNames.HERSTELLER) String hersteller,
            @JsonProperty(value = ApiNames.BAUZEIT) LocalDate bauzeit,
            @JsonProperty(value = ApiNames.ANZAHL) Integer anzahl,
            @JsonProperty(value = ApiNames.BETREIBSNUMMER) String betreibsNummer,
            @JsonProperty(value = ApiNames.ANTRIEB) IAntrieb antrieb,
            @JsonProperty(value = ApiNames.ACHSFOLG) IAchsfolg achsfolg,
            @JsonProperty(value = ApiNames.ANFAHRZUGKRAFT) BigDecimal anfahrzugkraft,
            @JsonProperty(value = ApiNames.LEISTUNG) BigDecimal leistung,
            @JsonProperty(value = ApiNames.DIENSTGEWICHT) BigDecimal dienstgewicht,
            @JsonProperty(value = ApiNames.GESCHWINDIGKEIT) Integer geschwindigkeit,
            @JsonProperty(value = ApiNames.LANGE) BigDecimal lange,
            @JsonProperty(value = ApiNames.AUSSERDIENST) LocalDate ausserdienst,
            @JsonProperty(value = ApiNames.DMTREIBRAD) BigDecimal dmTreibrad,
            @JsonProperty(value = ApiNames.DMLAUFRADVORN) BigDecimal dmLaufradVorn,
            @JsonProperty(value = ApiNames.DMLAUFRADHINTEN) BigDecimal dmLaufradHinten,
            @JsonProperty(value = ApiNames.ZYLINDER) Integer zylinder,
            @JsonProperty(value = ApiNames.DMZYLINDER) BigDecimal dmZylinder,
            @JsonProperty(value = ApiNames.KOLBENHUB) BigDecimal kolbenhub,
            @JsonProperty(value = ApiNames.KESSELUEBERDRUCK) BigDecimal kesselueberdruck,
            @JsonProperty(value = ApiNames.ROSTFLAECHE) BigDecimal rostflaeche,
            @JsonProperty(value = ApiNames.UEBERHITZERFLAECHE) BigDecimal ueberhitzerflaeche,
            @JsonProperty(value = ApiNames.WASSERVORRAT) BigDecimal wasservorrat,
            @JsonProperty(value = ApiNames.VERDAMPFUNG) BigDecimal verdampfung,
            @JsonProperty(value = ApiNames.FAHRMOTOREN) Integer fahrmotoren,
            @JsonProperty(value = ApiNames.MOTORBAUART) String motorbauart,
            @JsonProperty(value = ApiNames.LEISTUNGSUBERTRAGUNG) LeistungsUbertragung leistungsUbertragung,
            @JsonProperty(value = ApiNames.REICHWEITE) BigDecimal reichweite,
            @JsonProperty(value = ApiNames.KAPAZITAT) BigDecimal kapazitaet,
            @JsonProperty(value = ApiNames.KLASSE) Integer klasse,
            @JsonProperty(value = ApiNames.SITZPLATZEKL1) Integer sitzPlatzeKL1,
            @JsonProperty(value = ApiNames.SITZPLATZEKL2) Integer sitzPlatzeKL2,
            @JsonProperty(value = ApiNames.SITZPLATZEKL3) Integer sitzPlatzeKL3,
            @JsonProperty(value = ApiNames.SITZPLATZEKL4) Integer sitzPlatzeKL4,
            @JsonProperty(value = ApiNames.AUFBAU) String aufbauten,
            @JsonProperty(value = ApiNames.TRIEBKOEPFE) Integer triebkoepfe,
            @JsonProperty(value = ApiNames.MITTELWAGEN) Integer mittelwagen,
            @JsonProperty(value = ApiNames.DREHGESTELLBAUART) String drehgestellbauart,
            @JsonProperty(value = ApiNames.ANMERKUNG) String anmerkung,
            @JsonProperty(value = ApiNames.ABBILDUNG) String abbildungStr,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        Vorbild entity = new Vorbild(id, gattung, unterKategorie, bahnverwaltung, hersteller, bauzeit, anzahl, betreibsNummer, antrieb, achsfolg, anmerkung, anfahrzugkraft, leistung, dienstgewicht,
                geschwindigkeit, lange, ausserdienst, dmTreibrad, dmLaufradVorn, dmLaufradHinten, zylinder, dmZylinder, kolbenhub, kesselueberdruck, rostflaeche, ueberhitzerflaeche,
                wasservorrat, verdampfung, fahrmotoren, anmerkung, leistungsUbertragung, reichweite, kapazitaet, klasse, sitzPlatzeKL1, sitzPlatzeKL2, sitzPlatzeKL3,
                sitzPlatzeKL4, aufbauten, triebkoepfe, mittelwagen, drehgestellbauart, deleted);

        debug("created: " + entity);

        return entity;
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Vorbild by name", response = Vorbild.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        try {
            return super.get(new VorbildKey(findGattung(name, false)));
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Vorbilden by example", response = Vorbild.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Vorbild id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.GATTUNG, value = "The rolling stock class", example = "BR 89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KATEGORIE, value = "Category code", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.UNTER_KATEGORIE, value = "Sub category code", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAHNVERWALTUNG, value = "Railway company code", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.HERSTELLER, value = "Manufacturer", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAUZEIT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANZAHL, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BETREIBSNUMMER, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANTRIEB, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ACHSFOLG, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANFAHRZUGKRAFT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LEISTUNG, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DIENSTGEWICHT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.GESCHWINDIGKEIT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LANGE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.AUSSERDIENST, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DMTREIBRAD, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DMLAUFRADVORN, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DMLAUFRADHINTEN, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ZYLINDER, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DMZYLINDER, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KOLBENHUB, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KESSELUEBERDRUCK, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ROSTFLAECHE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.UEBERHITZERFLAECHE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.WASSERVORRAT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.VERDAMPFUNG, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STEUERUNG, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.FAHRMOTOREN, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MOTORBAUART, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LEISTUNGSUBERTRAGUNG, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.REICHWEITE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KAPAZITAT, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KLASSE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SITZPLATZEKL1, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SITZPLATZEKL2, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SITZPLATZEKL3, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SITZPLATZEKL4, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.AUFBAU, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.TRIEBKOEPFE, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MITTELWAGEN, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DREHGESTELLBAUART, value = "", example = "", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "if true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Vorbild", response = Vorbild.class)
    public Response add(Vorbild entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Vorbild by name", response = Vorbild.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Vorbild entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Vorbild by name", response = Vorbild.class)
    public Response delete(@PathParam(ApiPaths.ID_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PUT
    @Path(ApiPaths.ABBILDUNG_PART)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates thr picture for a named Vorbild", response = Vorbild.class)
    public Response updateAbbildung(@PathParam(ApiPaths.NAME_PARAM_NAME) String name,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DETAIL) FormDataContentDisposition fileDetail,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DATA) InputStream fileData) {
        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(fileDetail, fileData, AcceptableMediaTypes.IMAGES)) {
                return getResponse(badRequest(null, "Invalid file '" + fileDetail.getFileName() + "'"));
            }

            IVorbild vorbild = findVorbild(name, false);

            if (vorbild != null) {
                java.nio.file.Path file = handler.upload(ApiNames.VORBILD, new String[] { name }, fileDetail, fileData);

                vorbild.setAbbildung(file);

                getPersister().update((Vorbild) vorbild);

                return getResponse(ok(vorbild));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.ABBILDUNG_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Removes the picture from a named Vorbild", response = Vorbild.class)
    public Response deleteAbbildung(@PathParam(ApiPaths.ID_PARAM_NAME) String name) {
        try {
            IVorbild vorbild = findVorbild(name, false);

            if (vorbild != null && vorbild.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(vorbild.getAbbildung());

                vorbild.setAbbildung(null);

                getPersister().update((Vorbild) vorbild);

                return getResponse(ok(vorbild));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }
}
