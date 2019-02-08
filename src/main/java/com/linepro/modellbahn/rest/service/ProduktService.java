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
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.AufbauDeserializer;
import com.linepro.modellbahn.rest.json.serialization.BahnverwaltungDeserializer;
import com.linepro.modellbahn.rest.json.serialization.DecoderTypDeserializer;
import com.linepro.modellbahn.rest.json.serialization.EpochDeserializer;
import com.linepro.modellbahn.rest.json.serialization.HerstellerDeserializer;
import com.linepro.modellbahn.rest.json.serialization.KupplungDeserializer;
import com.linepro.modellbahn.rest.json.serialization.LichtDeserializer;
import com.linepro.modellbahn.rest.json.serialization.MassstabDeserializer;
import com.linepro.modellbahn.rest.json.serialization.MotorTypDeserializer;
import com.linepro.modellbahn.rest.json.serialization.ProduktDeserializer;
import com.linepro.modellbahn.rest.json.serialization.SonderModellDeserializer;
import com.linepro.modellbahn.rest.json.serialization.SpurweiteDeserializer;
import com.linepro.modellbahn.rest.json.serialization.SteuerungDeserializer;
import com.linepro.modellbahn.rest.json.serialization.UnterKategorieDeserializer;
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

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.PRODUKT)
@Path(ApiPaths.PRODUKT)
public class ProduktService extends AbstractItemService<ProduktKey, IProdukt> {

    private final IPersister<IProduktTeil> teilPersister;
    
    public ProduktService() {
        super(IProdukt.class);

        teilPersister = StaticPersisterFactory.get().createPersister(IProduktTeil.class);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static Produkt create() {
        return new Produkt();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static Produkt create(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.HERSTELLER)
            @JsonDeserialize(using= HerstellerDeserializer.class) IHersteller hersteller,
            @JsonProperty(value = ApiNames.BESTELL_NR) String bestellNr,
            @JsonProperty(value = ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value = ApiNames.UNTER_KATEGORIE)
            @JsonDeserialize(using= UnterKategorieDeserializer.class) IUnterKategorie unterKategorie,
            @JsonProperty(value = ApiNames.MASSSTAB)
            @JsonDeserialize(using= MassstabDeserializer.class) IMassstab massstab,
            @JsonProperty(value = ApiNames.SPURWEITE)
            @JsonDeserialize(using= SpurweiteDeserializer.class) ISpurweite spurweite,
            @JsonProperty(value = ApiNames.BETREIBSNUMMER) String betreibsNummer,
            @JsonProperty(value = ApiNames.EPOCH)
            @JsonDeserialize(using= EpochDeserializer.class) IEpoch epoch,
            @JsonProperty(value = ApiNames.BAHNVERWALTUNG)
            @JsonDeserialize(using= BahnverwaltungDeserializer.class) IBahnverwaltung bahnverwaltung,
            @JsonProperty(value = ApiNames.GATTUNG) IGattung gattung,
            @JsonProperty(value = ApiNames.BAUZEIT) LocalDate bauzeit,
            @JsonProperty(value = ApiNames.ACHSFOLG) IAchsfolg achsfolg,
            @JsonProperty(value = ApiNames.VORBILD) IVorbild vorbild,
            @JsonProperty(value = ApiNames.ANMERKUNG) String anmerkung,
            @JsonProperty(value = ApiNames.SONDERMODELL)
            @JsonDeserialize(using= SonderModellDeserializer.class) ISonderModell sondermodell,
            @JsonProperty(value = ApiNames.AUFBAU)
            @JsonDeserialize(using= AufbauDeserializer.class)  IAufbau aufbau,
            @JsonProperty(value = ApiNames.LICHT)
            @JsonDeserialize(using= LichtDeserializer.class) ILicht licht,
            @JsonProperty(value = ApiNames.KUPPLUNG)
            @JsonDeserialize(using= KupplungDeserializer.class) IKupplung kupplung,
            @JsonProperty(value = ApiNames.STEUERUNG)
            @JsonDeserialize(using= SteuerungDeserializer.class) ISteuerung steuerung,
            @JsonProperty(value = ApiNames.DECODER_TYP)
            @JsonDeserialize(using= DecoderTypDeserializer.class) IDecoderTyp decoderTyp,
            @JsonProperty(value = ApiNames.MOTOR_TYP)
            @JsonDeserialize(using= MotorTypDeserializer.class) IMotorTyp motorTyp,
            @JsonProperty(value = ApiNames.LANGE) BigDecimal lange,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
        return new Produkt(id,
                hersteller,
                bestellNr,
                bezeichnung,
                unterKategorie,
                massstab,
                spurweite,
                epoch,
                bahnverwaltung,
                gattung,
                betreibsNummer,
                bauzeit,
                vorbild,
                achsfolg,
                anmerkung,
                sondermodell,
                aufbau,
                licht,
                kupplung,
                steuerung,
                decoderTyp,
                motorTyp,
                lange,
                deleted);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ProduktTeil createProduktTeil() {
        return new ProduktTeil();
    }
    
    @JsonCreator(mode= Mode.PROPERTIES)
    public static ProduktTeil createProduktTeil(@JsonProperty(value = ApiNames.ID) Long id,
            @JsonProperty(value = ApiNames.PRODUKT)
            @JsonDeserialize(using= ProduktDeserializer.class) IProdukt produkt,
            @JsonProperty(value = ApiNames.TEIL)
            @JsonDeserialize(using= ProduktDeserializer.class) IProdukt teil,
            @JsonProperty(value = ApiNames.ANZAHL) Integer anzahl,
            @JsonProperty(value = ApiNames.DELETED) Boolean deleted) {
       return new ProduktTeil(id, produkt, teil, anzahl, deleted);
    }

    @GET
    @Path(ApiPaths.PRODUKT_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response get(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.get(new ProduktKey(findHersteller(herstellerStr, false), bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Produkten by example", response = Produkt.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Produkt id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.HERSTELLER, value = "Produkt manufacturer", example = "MARKLIN", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BESTELL_NR, value = "Produkt part number", example = "3000", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Produkt description", example = "Dampftenderlok BR 89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.UNTER_KATEGORIE, value = "Category and subcategory", example = "[\"LOKOMOTIV\",\"DAMPF\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MASSSTAB, value = "Produkt scale", example = "H0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SPURWEITE, value = "Produkt track gauge", example = "H0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.EPOCH, value = "Produkt era", example = "III", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAHNVERWALTUNG, value = "Produkt railway company", example = "DB", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.GATTUNG, value = "Produkt rolling stock class", example = "BR89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BETREIBSNUMMER, value = "Produkt service number", example = "89 006", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAUZEIT, value = "Produkt construction date", example = "1934-01-01", dataType = "java.time.LocalDate", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.VORBILD, value = "Produkt prototype", example = "BR89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ACHSFOLG, value = "Produkt axle configuration", example = "C1HT", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANMERKUNG, value = "Produkt remarks", example = "Aus set 29400", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SONDERMODELL, value = "Produkt SonderModell", example = "MHI", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.AUFBAU, value = "Produkt construction", example = "LK", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LICHT, value = "Produkt light configuration", example = "L1V", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KUPPLUNG, value = "Produkt coupling configuration", example = "RELEX", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STEUERUNG, value = "Produkt control method", example = "FRU", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER_TYP, value = "Produkt decoder type", example = "[\"ESU\",\"62400\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MOTOR_TYP, value = "Produkt motor type", example = "SFCM", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LANGE, value = "Produkt length", example = "11.0", dataType = "Number", paramType = "query"),
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
    @ApiOperation(code = 201, value = "Adds a Produkt", response = Produkt.class)
    public Response add(Produkt entity) {
        try {
            return super.add(entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.PRODUKT_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, Produkt entity) {
        try {
            return super.update(new ProduktKey(findHersteller(herstellerStr, false), bestellNr), entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Produkt by hersteller and bestell nr")
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(new ProduktKey(findHersteller(herstellerStr, false), bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @POST
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a sub produkt  a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response addTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, ProduktTeil teil) {
        try {
            logPost(String.format(ApiPaths.PRODUKT_TEIL_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + teil);

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, true);

            if (produkt == null) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            if (cyclic(produkt, teil.getTeil())) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }
            
            teil.setProdukt(produkt);
        
            produkt.addTeil(teil);

            getPersister().update(produkt);

            return getResponse(created(), teil, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private boolean cyclic(IProdukt produkt, IProdukt teil) {
        return produkt.equals(teil);
    }

    @PUT
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a sub produkt a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr, Integer anzahl) {
        try {
            logPut(String.format(ApiPaths.PRODUKT_TEIL_LOG, getEntityClassName(), herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr) + ":" + anzahl);

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, true);

            if (produkt == null) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IProduktTeil produktTeil = findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

            if (produktTeil == null) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_TEIL_DOES_NOT_EXIST, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr)));
            }

			produktTeil.setAnzahl(anzahl);

            produktTeil = getTeilPersister().update(produktTeil);

            return getResponse(created(), produktTeil, true);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a sub produkt for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logDelete(String.format(ApiPaths.PRODUKT_TEIL_LOG, getEntityClassName(), herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, true);

            if (produkt == null) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr)));
            }

            IProduktTeil produktTeil = findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

            if (produktTeil == null) {
                return getResponse(badRequest(getMessage(ApiMessages.PRODUKT_TEIL_DOES_NOT_EXIST, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr)));
            }

            produkt.removeTeil(produktTeil);

            getPersister().update(produkt);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PUT
    @Path(ApiPaths.PRODUKT_ABBILDUNG)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateAbbildung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,  
            @FormDataParam("file") FormDataBodyPart body) {
        logPut(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + contentDispositionHeader.getFileName());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(body, AcceptableMediaTypes.IMAGE_TYPES)) {
                return getResponse(badRequest(getMessage(ApiMessages.INVALID_FILE, contentDispositionHeader.getFileName())));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, contentDispositionHeader, fileInputStream);

                produkt.setAbbildung(file);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_ABBILDUNG)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteAbbildung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, getEntityClassName(), herstellerStr, bestellNr));
        
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAbbildung());

                produkt.setAbbildung(null);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @PUT
    @Path(ApiPaths.PRODUKT_ANLEITUNGEN)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateAnleitungen(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,  
            @FormDataParam("file") FormDataBodyPart body) {
        logPut(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + contentDispositionHeader.getFileName());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(body, AcceptableMediaTypes.DOCUMENT_TYPES)) {
                return getResponse(badRequest(getMessage(ApiMessages.INVALID_FILE, contentDispositionHeader.getFileName())));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, contentDispositionHeader, fileInputStream);

                produkt.setAnleitungen(file);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_ANLEITUNGEN)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteAnleitungen(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, getEntityClassName(), herstellerStr, bestellNr));
        
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAnleitungen());

                produkt.setAnleitungen(null);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @PUT
    @Path(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateExplosionszeichnung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,  
            @FormDataParam("file") FormDataBodyPart body) {
        logPut(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + contentDispositionHeader.getFileName());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(body, AcceptableMediaTypes.DOCUMENT_TYPES)) {
                return getResponse(badRequest(getMessage(ApiMessages.INVALID_FILE, contentDispositionHeader.getFileName())));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, contentDispositionHeader, fileInputStream);

                produkt.setExplosionszeichnung(file);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteExplosionszeichnung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, getEntityClassName(), herstellerStr, bestellNr));
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getExplosionszeichnung());

                produkt.setExplosionszeichnung(null);

                getPersister().update(produkt);

                return getResponse(ok(), produkt, true);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return getResponse(notFound());
    }

    private IPersister<IProduktTeil> getTeilPersister() {
        return teilPersister;
    }
}
