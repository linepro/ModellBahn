package com.linepro.modellbahn.rest.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

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

import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.impl.Produkt;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.model.keys.ProduktTeilKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.AcceptableMediaTypes;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.FileUploadHandler;
import com.linepro.modellbahn.rest.util.IFileUploadHandler;
import com.linepro.modellbahn.util.StaticContentFinder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.PRODUKT, description = "Produkt maintenance")
@Path(ApiPaths.PRODUKT)
public class ProduktService extends AbstractItemService<ProduktKey, Produkt> {

    private final IPersister<ProduktTeil> teilPersister;
    
    public ProduktService() {
        super(Produkt.class);

        teilPersister = StaticPersisterFactory.get().createPersister(ProduktTeil.class);
    }

    @JsonCreator
    public Produkt create(@JsonProperty(value=ApiNames.ID) Long id,
            @JsonProperty(value=ApiNames.HERSTELLER) String herstellerStr,
            @JsonProperty(value=ApiNames.BESTELL_NR) String bestellNr,
            @JsonProperty(value=ApiNames.BEZEICHNUNG) String bezeichnung,
            @JsonProperty(value=ApiNames.UNTER_KATEGORIE) UnterKategorie unterKategorie,
            @JsonProperty(value=ApiNames.MASSSTAB) String massstabStr,
            @JsonProperty(value=ApiNames.SPURWEITE) String spurweiteStr,
            @JsonProperty(value=ApiNames.BETREIBSNUMMER) String betreibsNummer,
            @JsonProperty(value=ApiNames.EPOCH) String epochStr,
            @JsonProperty(value=ApiNames.BAHNVERWALTUNG) String bahnverwaltungStr,
            @JsonProperty(value=ApiNames.GATTUNG) String gattungStr,
            @JsonProperty(value=ApiNames.BAUZEIT) Date bauzeit,
            @JsonProperty(value=ApiNames.ACHSFOLG) String achsfolgStr,
            @JsonProperty(value=ApiNames.VORBILD) String vorbildStr,
            @JsonProperty(value=ApiNames.ANMERKUNG) String anmerkung,
            @JsonProperty(value=ApiNames.SONDERMODELL) String sondermodellStr,
            @JsonProperty(value=ApiNames.AUFBAU) String aufbauStr,
            @JsonProperty(value=ApiNames.LICHT) String lichtStr,
            @JsonProperty(value=ApiNames.KUPPLUNG) String kupplungStr,
            @JsonProperty(value=ApiNames.STEUERUNG) String steuerungStr,
            @JsonProperty(value=ApiNames.DECODER_TYP) DecoderTyp decoderTyp,
            @JsonProperty(value=ApiNames.MOTOR_TYP) String motorTypStr,
            @JsonProperty(value=ApiNames.LANGE) BigDecimal lange,
            @JsonProperty(value=ApiNames.DELETED) Boolean deleted) throws Exception {
        // Just see if Jackson can work out the embedded objects...
        IHersteller hersteller = findHersteller(herstellerStr, false);
        IMassstab massstab = findMassstab(massstabStr, false);
        ISpurweite spurweite = findSpurweite(spurweiteStr, false);
        IEpoch epoch = findEpoch(epochStr, false);
        IBahnverwaltung bahnverwaltung = findBahnverwaltung(bahnverwaltungStr, false);
        IGattung gattung = findGattung(gattungStr, false);
        IVorbild vorbild = findVorbild(gattungStr, false);
        IAchsfolg achsfolg = findAchsfolg(achsfolgStr, false);
        ISonderModell sondermodell = findSonderModell(sondermodellStr, false);
        IAufbau aufbau = findAufbau(aufbauStr, false);
        ILicht licht = findLicht(lichtStr, false);
        IKupplung kupplung = findKupplung(kupplungStr, false);
        ISteuerung steuerung = findSteuerung(steuerungStr, false);
        IMotorTyp motorTyp = findMotorTyp(motorTypStr, false);

        Produkt entity = new Produkt(id,
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

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    ProduktTeil createProduktTeil(@JsonProperty(value=ApiNames.ID) Long id,
            @JsonProperty(value=ApiNames.PRODUKT) Produkt produkt,
            @JsonProperty(value=ApiNames.TEIL) Produkt teil,
            @JsonProperty(value=ApiNames.ANZAHL) Integer anzahl,
            @JsonProperty(value=ApiNames.DELETED) Boolean deleted) {
        ProduktTeil entity = new ProduktTeil(id, produkt, teil, anzahl, deleted);

        debug("created: " + entity);

        return entity;
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
            return getResponse(serverError(e));
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Produkten by example", response = Produkt.class, responseContainer = "List")
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds a Produkt", response = Produkt.class)
    public Response add(Produkt entity) {
        try {
            return super.add(entity);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.PRODUKT_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Updates a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, Produkt entity) {
        try {
            return super.update(new ProduktKey(findHersteller(herstellerStr, false), bestellNr), entity);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes a Produkt by hersteller and bestell nr")
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(new ProduktKey(findHersteller(herstellerStr, false), bestellNr));
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @POST
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds a sub produkt  a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response addTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logPost(herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr);

            Produkt produkt = (Produkt) findProdukt(herstellerStr, bestellNr, true);

            if (produkt == null) {
                return getResponse(badRequest(null, "Produkt " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            
            Produkt teil =  (Produkt) findProdukt(teilHerstellerStr, teilBestellNr, true);

            if (teil == null) {
                return getResponse(badRequest(null, "Produkt " + teilHerstellerStr + "/" + teilBestellNr + " does not exist"));
            }

            // TODO: check for cycles

            ProduktTeil produktTeil = new ProduktTeil(null, produkt, teil, 1, false);

            produkt.addTeil(produktTeil);

            getPersister().update(produkt);

            return getResponse(created(), produktTeil, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Updates a sub produkt a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logPut(herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr);

            ProduktTeil produktTeil = findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

            if (produktTeil == null) {
                return getResponse(badRequest(null, "ProduktTeil " + herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr + " does not exist"));
            }

            @SuppressWarnings("unused")
            IProdukt produkt = produktTeil.getProdukt();

            // TODO: update produktTeil

            produktTeil = getTeilPersister().update(produktTeil);

            return getResponse(created(), produktTeil, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_TEIL_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes a sub produkt for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logDelete(herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr);

            ProduktTeil produktTeil = findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

            if (produktTeil == null) {
                return getResponse(badRequest(null, "ProduktTeil " + herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr + " does not exist"));
            }

            Produkt produkt = (Produkt) produktTeil.getProdukt();

            produkt.removeTeil(produktTeil);

            //getProduktTeilPersister().delete(produktTeil);

            getPersister().update(produkt);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.PRODUKT_ABBILDUNG)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds or updates the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateAbbildung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DETAIL) FormDataContentDisposition fileDetail,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DATA) InputStream fileData) {
        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(fileDetail, fileData, AcceptableMediaTypes.IMAGES)) {
                return getResponse(badRequest(null, "Invalid file '" + fileDetail.getFileName() + "'"));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, fileDetail, fileData);

                produkt.setAbbildung(file);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_ABBILDUNG)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteAbbildung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAbbildung());

                produkt.setAbbildung(null);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @PUT
    @Path(ApiPaths.PRODUKT_ANLEITUNGEN)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds or updates the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateAnleitungen(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
                                      @FormDataParam(ApiPaths.MULTIPART_FILE_DETAIL) FormDataContentDisposition fileDetail,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DATA) InputStream fileData) {
        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(fileDetail, fileData, AcceptableMediaTypes.DOCUMENTS)) {
                return getResponse(badRequest(null, "Invalid file '" + fileDetail.getFileName() + "'"));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, fileDetail, fileData);

                produkt.setAnleitungen(file);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_ANLEITUNGEN)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteAnleitungen(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAnleitungen());

                produkt.setAnleitungen(null);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @PUT
    @Path(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds or updates the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response updateExplosionszeichnung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
                                              @FormDataParam(ApiPaths.MULTIPART_FILE_DETAIL) FormDataContentDisposition fileDetail,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DATA) InputStream fileData) {
        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(fileDetail, fileData, AcceptableMediaTypes.DOCUMENTS)) {
                return getResponse(badRequest(null, "Invalid file '" + fileDetail.getFileName() + "'"));
            }

            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, fileDetail, fileData);

                produkt.setExplosionszeichnung(file);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    @DELETE
    @Path(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public Response deleteExplosionszeichnung(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getExplosionszeichnung());

                produkt.setExplosionszeichnung(null);

                getPersister().update((Produkt) produkt);

                return getResponse(ok(produkt));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }

    private IPersister<ProduktTeil> getTeilPersister() {
        return teilPersister;
    }
}
