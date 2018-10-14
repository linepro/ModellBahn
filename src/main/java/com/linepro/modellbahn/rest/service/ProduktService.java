package com.linepro.modellbahn.rest.service;

import java.io.File;
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IAchsfolg;
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
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.PRODUKT)
public class ProduktService extends AbstractItemService<ProduktKey, Produkt> {

    protected final IPersister<ProduktTeil> teilPersister;
    
    public ProduktService() {
        super(Produkt.class);

        teilPersister = StaticPersisterFactory.get().createPersister(ProduktTeil.class);
    }

    @JsonCreator
    public Produkt create(@JsonProperty(value=ApiNames.ID, required=false) Long id,
            @JsonProperty(value=ApiNames.HERSTELLER, required=false) String herstellerStr,
            @JsonProperty(value=ApiNames.BESTELL_NR, required=false) String bestellNr,
            @JsonProperty(value=ApiNames.BEZEICHNUNG, required=false) String bezeichnung,
            @JsonProperty(value=ApiNames.UNTER_KATEGORIE, required=false) UnterKategorie unterKategorie,
            @JsonProperty(value=ApiNames.MASSSTAB, required=false) String massstabStr,
            @JsonProperty(value=ApiNames.SPURWEITE, required=false) String spurweiteStr,
            @JsonProperty(value=ApiNames.BETREIBSNUMMER, required=false) String betreibsNummer,
            @JsonProperty(value=ApiNames.EPOCH, required=false) String epochStr,
            @JsonProperty(value=ApiNames.BAHNVERWALTUNG, required=false) String bahnverwaltungStr,
            @JsonProperty(value=ApiNames.GATTUNG, required=false) String gattungStr,
            @JsonProperty(value=ApiNames.BAUZEIT, required=false) Date bauzeit,
            @JsonProperty(value=ApiNames.ACHSFOLG, required=false) String achsfolgStr,
            @JsonProperty(value=ApiNames.VORBILD, required=false) String vorbildStr,
            @JsonProperty(value=ApiNames.ANMERKUNG, required=false) String anmerkung,
            @JsonProperty(value=ApiNames.SONDERMODELL, required=false) String sondermodellStr,
            @JsonProperty(value=ApiNames.AUFBAU, required=false) String aufbauStr,
            @JsonProperty(value=ApiNames.LICHT, required=false) String lichtStr,
            @JsonProperty(value=ApiNames.KUPPLUNG, required=false) String kupplungStr,
            @JsonProperty(value=ApiNames.STEUERUNG, required=false) String steuerungStr,
            @JsonProperty(value=ApiNames.DECODER_TYP, required=false) DecoderTyp decoderTyp,
            @JsonProperty(value=ApiNames.MOTOR_TYP, required=false) String motorTypStr,
            @JsonProperty(value=ApiNames.LANGE, required=false) BigDecimal lange,
            @JsonProperty(value=ApiNames.ANLEITUNGEN, required=false) String anleitungenStr,
            @JsonProperty(value=ApiNames.EXPLOSIONSZEICHNUNG, required=false) String explosionszeichnungStr,
            @JsonProperty(value=ApiNames.ABBILDUNG, required=false) String abbildungStr,
            @JsonProperty(value=ApiNames.DELETED, required=false) Boolean deleted) throws Exception {
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

        File anleitungen = null;
        File abbildung = null;
        File explosionszeichnung = null;
        
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
                anleitungen,
                explosionszeichnung,
                abbildung,
                deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    ProduktTeil createProduktTeil(@JsonProperty(value=ApiNames.ID, required=false) Long id, 
            @JsonProperty(value=ApiNames.PRODUKT, required=false) Produkt produkt, 
            @JsonProperty(value=ApiNames.TEIL, required=false) Produkt teil, 
            @JsonProperty(value=ApiNames.ANZAHL, required=false) Integer anzahl,
            @JsonProperty(value=ApiNames.DELETED, required=false) Boolean deleted) {
        ProduktTeil entity = new ProduktTeil(id, produkt, teil, anzahl, deleted);

        debug("created: " + entity);

        return entity;
    }

    @GET
    @Path(ApiPaths.PRODUKT_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
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
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
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
    public Response updateTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logPut(herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr);

            ProduktTeil produktTeil = (ProduktTeil) findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

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
    public Response deleteTeil(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathParam(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathParam(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        try {
            logDelete(herstellerStr + "/" + bestellNr + teilHerstellerStr + "/" + teilBestellNr);

            ProduktTeil produktTeil = (ProduktTeil) findProduktTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, true);

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

    private ProduktTeil findProduktTeil(String herstellerStr, String bestellNr, String teilHerstellerStr, String teilBestellNr, boolean eager) throws Exception {
        return getTeilPersister().findByKey(new ProduktTeilKey(findProdukt(herstellerStr, bestellNr, false), findProdukt(teilHerstellerStr, teilBestellNr, false)), eager);
    }

    protected IPersister<ProduktTeil> getTeilPersister() {
        return teilPersister;
    }
}
