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
import com.linepro.modellbahn.model.keys.NameKey;
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
public class VorbildService extends AbstractItemService<NameKey, Vorbild> {

    public VorbildService() {
        super(Vorbild.class);
    }

    @JsonCreator
    public Vorbild create(@JsonProperty(value=ApiNames.ID) Long id,
            @JsonProperty(value=ApiNames.GATTUNG) IGattung gattung,
            @JsonProperty(value=ApiNames.UNTER_KATEGORIE, required=true) IUnterKategorie unterKategorie,
            @JsonProperty(value=ApiNames.BAHNVERWALTUNG) IBahnverwaltung bahnverwaltung,
            @JsonProperty(value=ApiNames.HERSTELLER) String hersteller,
            @JsonProperty(value=ApiNames.BAUZEIT) LocalDate bauzeit,
            @JsonProperty(value=ApiNames.ANZAHL) Integer anzahl,
            @JsonProperty(value=ApiNames.BETREIBSNUMMER) String betreibsNummer,
            @JsonProperty(value=ApiNames.ANTRIEB) IAntrieb antrieb,
            @JsonProperty(value=ApiNames.ACHSFOLG) IAchsfolg achsfolg,
            @JsonProperty(value=ApiNames.ANFAHRZUGKRAFT) BigDecimal anfahrzugkraft,
            @JsonProperty(value=ApiNames.LEISTUNG) BigDecimal leistung,
            @JsonProperty(value=ApiNames.DIENSTGEWICHT) BigDecimal dienstgewicht,
            @JsonProperty(value=ApiNames.GESCHWINDIGKEIT) Integer geschwindigkeit,
            @JsonProperty(value=ApiNames.LANGE) BigDecimal lange,
            @JsonProperty(value=ApiNames.AUSSERDIENST) LocalDate ausserdienst,
            @JsonProperty(value=ApiNames.DMTREIBRAD) BigDecimal dmTreibrad,
            @JsonProperty(value=ApiNames.DMLAUFRADVORN) BigDecimal dmLaufradVorn,
            @JsonProperty(value=ApiNames.DMLAUFRADHINTEN) BigDecimal dmLaufradHinten,
            @JsonProperty(value=ApiNames.ZYLINDER) Integer zylinder,
            @JsonProperty(value=ApiNames.DMZYLINDER) BigDecimal dmZylinder,
            @JsonProperty(value=ApiNames.KOLBENHUB) BigDecimal kolbenhub,
            @JsonProperty(value=ApiNames.KESSELUEBERDRUCK) BigDecimal kesselueberdruck,
            @JsonProperty(value=ApiNames.ROSTFLAECHE) BigDecimal rostflaeche,
            @JsonProperty(value=ApiNames.UEBERHITZERFLAECHE) BigDecimal ueberhitzerflaeche,
            @JsonProperty(value=ApiNames.WASSERVORRAT) BigDecimal wasservorrat,
            @JsonProperty(value=ApiNames.VERDAMPFUNG) BigDecimal verdampfung,
            @JsonProperty(value=ApiNames.FAHRMOTOREN) Integer fahrmotoren,
            @JsonProperty(value=ApiNames.MOTORBAUART) String motorbauart,
            @JsonProperty(value=ApiNames.LEISTUNGSUEBERTRAGUNG) BigDecimal leistungsuebertragung,
            @JsonProperty(value=ApiNames.REICHWEITE) BigDecimal reichweite,
            @JsonProperty(value=ApiNames.KAPAZITAT) BigDecimal kapazitaet,
            @JsonProperty(value=ApiNames.KLASSE) Integer klasse,
            @JsonProperty(value=ApiNames.SITZPLATZEKL1) Integer sitzPlatzeKL1,
            @JsonProperty(value=ApiNames.SITZPLATZEKL2) Integer sitzPlatzeKL2,
            @JsonProperty(value=ApiNames.SITZPLATZEKL3) Integer sitzPlatzeKL3,
            @JsonProperty(value=ApiNames.SITZPLATZEKL4) Integer sitzPlatzeKL4,
            @JsonProperty(value=ApiNames.AUFBAU) String aufbauten,
            @JsonProperty(value=ApiNames.TRIEBZUGANZEIGEN) Boolean triebzugAnzeigen,
            @JsonProperty(value=ApiNames.TRIEBKOEPFE) Integer triebkoepfe,
            @JsonProperty(value=ApiNames.MITTELWAGEN) Integer mittelwagen,
            @JsonProperty(value=ApiNames.SITZPLATZETZKL1) Integer sitzPlatzeTZKL1,
            @JsonProperty(value=ApiNames.SITZPLATZETZKL2) Integer sitzPlatzeTzKL2,
            @JsonProperty(value=ApiNames.DREHGESTELLBAUART) String drehgestellbauart,
            @JsonProperty(value=ApiNames.ANMERKUNG) String anmerkung,
            @JsonProperty(value=ApiNames.ABBILDUNG) String abbildungStr,
            @JsonProperty(value=ApiNames.DELETED) Boolean deleted) {
        Vorbild entity = new Vorbild(id, gattung, unterKategorie, bahnverwaltung, hersteller, bauzeit, anzahl, betreibsNummer, antrieb, achsfolg, anfahrzugkraft, leistung, dienstgewicht,
                geschwindigkeit, lange, ausserdienst, dmTreibrad, dmLaufradVorn, dmLaufradHinten, zylinder, dmZylinder, kolbenhub, kesselueberdruck, rostflaeche, ueberhitzerflaeche,
                wasservorrat, verdampfung, fahrmotoren, anmerkung, leistungsuebertragung, reichweite, kapazitaet, klasse, sitzPlatzeKL1, sitzPlatzeKL2, sitzPlatzeKL3,
                sitzPlatzeKL4, aufbauten, triebzugAnzeigen, triebkoepfe, mittelwagen, sitzPlatzeTZKL1, sitzPlatzeTzKL2, drehgestellbauart, anmerkung, deleted);

        debug("created: " + entity);

        return entity;
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Vorbild by name", response = Vorbild.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Vorbilden by example", response = Vorbild.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Vorbild's id", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.GATTUNG, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.UNTER_KATEGORIE, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.BAHNVERWALTUNG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.HERSTELLER, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.BAUZEIT, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.ANZAHL, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.BETREIBSNUMMER, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.ANTRIEB, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.ACHSFOLG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.ANFAHRZUGKRAFT, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.LEISTUNG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.DIENSTGEWICHT, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.GESCHWINDIGKEIT, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.LANGE, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.AUSSERDIENST, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.DMTREIBRAD, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.DMLAUFRADVORN, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.DMLAUFRADHINTEN, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.ZYLINDER, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.DMZYLINDER, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.KOLBENHUB, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.KESSELUEBERDRUCK, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.ROSTFLAECHE, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.UEBERHITZERFLAECHE, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.WASSERVORRAT, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.VERDAMPFUNG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.STEUERUNG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.FAHRMOTOREN, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.MOTORBAUART, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.LEISTUNGSUEBERTRAGUNG, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.REICHWEITE, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.KAPAZITAT, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.KLASSE, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.SITZPLATZEKL1, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.SITZPLATZEKL2, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.SITZPLATZEKL3, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.SITZPLATZEKL4, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.AUFBAU, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.TRIEBZUGANZEIGEN, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.TRIEBKOEPFE, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.MITTELWAGEN, value = "", required = false, dataType = "String", paramType = "query"), 
        @ApiImplicitParam( name =  ApiNames.SITZPLATZETZKL1, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.SITZPLATZETZKL2, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name =  ApiNames.DREHGESTELLBAUART, value = "", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "true if Vorbild is deleted", required = false, dataType = "Boolean", paramType = "query")
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
