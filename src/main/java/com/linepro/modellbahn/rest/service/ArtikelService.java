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

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linepro.modellbahn.model.IAnderung;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.impl.Anderung;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.keys.AnderungKey;
import com.linepro.modellbahn.model.keys.ArtikelKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DecoderDeserializer;
import com.linepro.modellbahn.rest.json.serialization.KupplungDeserializer;
import com.linepro.modellbahn.rest.json.serialization.LichtDeserializer;
import com.linepro.modellbahn.rest.json.serialization.LocalDateDeserializer;
import com.linepro.modellbahn.rest.json.serialization.MotorTypDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ProduktDeserializer;
import com.linepro.modellbahn.rest.json.serialization.SteuerungDeserializer;
import com.linepro.modellbahn.rest.json.serialization.WahrungDeserializer;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.AcceptableMediaTypes;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.FileUploadHandler;
import com.linepro.modellbahn.rest.util.IFileUploadHandler;
import com.linepro.modellbahn.util.StaticContentFinder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ARTIKEL)
@Path(ApiPaths.ARTIKEL)
public class ArtikelService extends AbstractItemService<ArtikelKey, IArtikel> {

    private final IPersister<IAnderung> anderungPersister;

    public ArtikelService() {
        super(IArtikel.class);

        anderungPersister = StaticPersisterFactory.get().createPersister(IAnderung.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static Artikel create() {
        return new Artikel();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static Artikel create(@JsonProperty(value = ApiNames.ARTIKEL_ID) Long id,
            @JsonProperty(value = ApiNames.PRODUKT)
            @JsonDeserialize(using= ProduktDeserializer.class) IProdukt produkt,
            @JsonProperty(value = ApiNames.KAUFDATUM)
            @JsonDeserialize(using = LocalDateDeserializer.class) LocalDate kaufdatum,
            @JsonProperty(value = ApiNames.WAHRUNG)
            @JsonDeserialize(using= WahrungDeserializer.class) IWahrung wahrung,
            @JsonProperty(value = ApiNames.PREIS) BigDecimal preis,
            @JsonProperty(value = ApiNames.STUCK) Integer stuck,
            @JsonProperty(value = ApiNames.VERBLEIBENDE) Integer verbleibende,
            @JsonProperty(value = ApiNames.STEUERUNG)
            @JsonDeserialize(using= SteuerungDeserializer.class) ISteuerung steuerung,
            @JsonProperty(value = ApiNames.MOTOR_TYP)
            @JsonDeserialize(using= MotorTypDeserializer.class) IMotorTyp motorTyp,
            @JsonProperty(value = ApiNames.LICHT)
            @JsonDeserialize(using= LichtDeserializer.class) ILicht licht,
            @JsonProperty(value = ApiNames.KUPPLUNG)
            @JsonDeserialize(using= KupplungDeserializer.class) IKupplung kupplung,
            @JsonProperty(value = ApiNames.DECODER)
            @JsonDeserialize(using= DecoderDeserializer.class) IDecoder decoder,
            @JsonProperty(value = ApiNames.NAMEN) String artikelNr,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.ANMERKUNG) String anmerkung,
            @JsonProperty(value = ApiNames.BELADUNG) String beladung,
            @JsonProperty(value = ApiNames.ABBILDUNG) String abbildungStr,
            @JsonProperty(value = ApiNames.STATUS) String statusStr,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        Status status = Status.valueOf(statusStr);
        
        return new Artikel(id, produkt, kaufdatum, wahrung, preis, stuck, 
                verbleibende, steuerung, motorTyp, licht, kupplung, decoder,
                artikelNr, bezeichnung, anmerkung, beladung, status, deleted);
    }

    @Override
    @GET
    @Path(ApiPaths.ARTIKEL_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Artikel by id", response = IArtikel.class)
    public Response get(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String id) {
        return super.get(new ArtikelKey(id));
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Artikeln by example", response = IArtikel.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Artikel id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PRODUKT, value = "Artikel manufacturer", example = "[\"MARKLIN\",\"3000\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KAUFDATUM, value = "Artikel purchase date", example = "1967-02-15", dataType = "java.time.LocalDate", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.WAHRUNG, value = "Artikel currency", example = "HKD", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PREIS, value = "Artikel price", example = "110.50", dataType = "Number", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STUCK, value = "Artikel count", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STEUERUNG, value = "Artikel control method code", example = "FRU", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MOTOR_TYP, value = "Artikel motor type code", example = "SFCM", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LICHT, value = "Artikel light configuration code", example = "L1K", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KUPPLUNG, value = "Artikel coupling configuration code", example = "RELEX", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER, value = "Artikel decoderId", example = "00001", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Artikel code", example = "00001", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Artikel description", example = "Aus set", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANMERKUNG, value = "Artikel remarks", dataType = "String", example = "My favorite", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BELADUNG, value = "Artikel load", dataType = "String", example = "Holz", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STATUS, value = "Artikel status", dataType = "String", example = "GEKAUFT", paramType = "query"),
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
    @ApiOperation(code = 201, value = "Adds an Artikel", response = IArtikel.class)
    public Response add(Artikel entity) {
        entity.setArtikelId(getPersister().getNextId());

        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.ARTIKEL_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Artikel by id", response = IArtikel.class)
    public Response update(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, Artikel entity) {
        return super.update(new ArtikelKey(artikelId), entity);
    }

    @Override
    @DELETE
    @Path(ApiPaths.ARTIKEL_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Artikel by id")
    public Response delete(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        return super.delete(new ArtikelKey(artikelId));
    }

    @PUT
    @Path(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds or updates the image for a named Artikel", response = IArtikel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response updateAbbildung(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,  
            @FormDataParam("file") FormDataBodyPart body) {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), artikelId) + ": " + contentDispositionHeader.getFileName());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(body, AcceptableMediaTypes.IMAGE_TYPES)) {
                return getResponse(badRequest(getMessage(ApiMessages.INVALID_FILE, contentDispositionHeader.getFileName())));
            }

            IArtikel artikel = findArtikel(artikelId, false);

            if (artikel != null) {
                java.nio.file.Path file = handler.upload(ApiNames.ARTIKEL, new String[] { artikelId }, contentDispositionHeader, fileInputStream);

                artikel.setAbbildung(file);

                getPersister().update(artikel);

                return getResponse(ok(), artikel, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes the image for a named Artikel", response = IArtikel.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public Response deleteAbbildung(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), artikelId));
        
        try {
            IArtikel artikel = findArtikel(artikelId, false);

            if (artikel != null && artikel.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(artikel.getAbbildung());

                artikel.setAbbildung(null);

                getPersister().update(artikel);

                return getResponse(ok(), artikel, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @POST
    @Path(ApiPaths.ANDERUNG_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a new changed to an article", response = IAnderung.class)
    public Response addAnderung(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, Anderung newAnderung) {
        try {
            logPost(String.format(ApiPaths.ANDERUNG_ROOT_LOG, getEntityClassName(), artikelId) + ": " + newAnderung);

            IArtikel artikel = findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId)));
            }

            artikel.addAnderung(newAnderung);

            getPersister().update(artikel);

            return getResponse(created(), newAnderung, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.ANDERUNG_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a change to an Article", response = IAnderung.class)
    public Response updateAnderung(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathParam(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId, Anderung newAnderung) {
        try {
            logPut(String.format(ApiPaths.ANDERUNG_LOG, getEntityClassName(), artikelId, anderungId) + ": " + newAnderung);

            IArtikel artikel = findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId)));
            }

            IAnderung anderung = findAnderung(artikel, anderungId, true);

            if (anderung == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ANDERUNG_DOES_NOT_EXIST, artikelId, anderungId)));
            }

            newAnderung.setArtikel(artikel);
            newAnderung.setAnderungId(anderungId);

            anderung = getAnderungPersister().merge(new AnderungKey(artikel, anderung.getAnderungId()), newAnderung);

            return getResponse(created(), anderung, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.ANDERUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Removes a change from an article")
    public Response deleteAnderung(@PathParam(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathParam(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId) {
        try {
            logDelete(String.format(ApiPaths.ANDERUNG_LOG, getEntityClassName(), artikelId, anderungId));

            IArtikel artikel = findArtikel(artikelId, true);

            if (artikel == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId)));
            }

            IAnderung anderung = findAnderung(artikelId, anderungId, true);

            if (anderung == null) {
                return getResponse(badRequest(getMessage(ApiMessages.ANDERUNG_DOES_NOT_EXIST, artikelId, anderungId)));
            }

            artikel.removeAnderung(anderung);

            // TODO: resequence anderungId for remaining items?
            
            getPersister().update(artikel);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private IPersister<IAnderung> getAnderungPersister() {
        return anderungPersister;
    }
}
