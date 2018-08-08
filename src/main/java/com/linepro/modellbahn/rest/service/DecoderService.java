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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IDecoderAdress;
import com.linepro.modellbahn.model.IDecoderCV;
import com.linepro.modellbahn.model.IDecoderFunktion;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypAdress;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.persistence.IIdGenerator;
import com.linepro.modellbahn.persistence.impl.IdGenerator;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * DecoderService. CRUD service for Decoder
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.DECODER)
public class DecoderService extends AbstractItemService<NameKey, Decoder> {

    public DecoderService() {
        super(Decoder.class);
    }

    @JsonCreator
    public Decoder create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.DECODER_TYP, required = false) DecoderTyp decoderTyp,
            @JsonProperty(value = ApiNames.PROTOKOLL, required = false) Protokoll protokoll,
            @JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.FAHRSTUFE, required = false) Integer fahrstufe,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Decoder entity = new Decoder(id, decoderTyp, protokoll, decoderId, bezeichnung, fahrstufe, deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderAdress createAdress(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.REIHE, required = false) Integer reihe,
            @JsonProperty(value = ApiNames.ADRESS_TYP, required = false) String adressTypStr,
            @JsonProperty(value = ApiNames.ADRESS, required = false) Integer adress,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);
        AdressTyp adressTyp = AdressTyp.valueOf(adressTypStr);

        DecoderAdress entity = new DecoderAdress(id, decoder, reihe, adressTyp, adress, deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderCV createCV(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.CV, required = false) Integer cvValue,
            @JsonProperty(value = ApiNames.WERT, required = false) Integer wert,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);

        IDecoderTypCV decoderTypCV = findDecoderTypCV(decoder.getDecoderTyp(), cvValue, true);

        DecoderCV entity = new DecoderCV(id, decoder, decoderTypCV, wert, deleted);

        debug("created: " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderFunktion createFunktion(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.REIHE, required = false) Integer reihe,
            @JsonProperty(value = ApiNames.FUNKTION, required = false) String funktion,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);

        IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(decoder.getDecoderTyp(), reihe, funktion, true);

        DecoderFunktion entity = new DecoderFunktion(id, decoder, decoderTypFunktion, bezeichnung, deleted);

        debug("created: " + entity);

        return entity;
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response addDecoder(@PathParam(ApiNames.HERSTELLER) String herstellerStr, @PathParam(ApiNames.BESTELL_NR) String bestellNr) {
        try {
            IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            IIdGenerator idGenerator = new IdGenerator(getPersister());
            
            Decoder decoder = new Decoder(null, decoderTyp, decoderTyp.getProtokoll(), idGenerator.getNextId(), decoderTyp.getBezeichnung(), decoderTyp.getFahrstufe(), false);
            
            decoder = getPersister().add(decoder);

            for (IDecoderTypAdress adress : decoderTyp.getAdressen()) {
                decoder.addAdress(new DecoderAdress(null, decoder, adress.getIndex(), adress.getAdressTyp(), adress.getWerkseinstellung(), false));
            }

            for (IDecoderTypCV cv : decoderTyp.getCVs()) {
                decoder.addCV(new DecoderCV(null, decoder, cv, cv.getWerkseinstellung(), false));
            }

            for (IDecoderTypFunktion funktion : decoderTyp.getFunktionen()) {
                decoder.addFunktion(new DecoderFunktion(null, decoder, funktion, funktion.getBezeichnung(), false));
            }

            decoder = getPersister().save(decoder);

            return getResponse(created(), decoder, true, true);
        } catch (Exception e) {
            return serverError(e).build();
        }
    }

    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId) {
        return super.get(decoderId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, Decoder entity) {
        return super.update(decoderId, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId) {
        return super.delete(decoderId);
    }

    @GET
    @Path(ApiPaths.DECODER_ADRESS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getAdress(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe) {
        return notFound().build();
    }

    @PUT
    @Path(ApiPaths.DECODER_ADRESS_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response updateAdress(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @QueryParam(ApiNames.WERT) Integer wert) {
        // Validate min(adressTyp) < adress < max(adressTyp) {TODO: find ranges for each}
        return notFound().build();
    }

    @GET
    @Path(ApiPaths.DECODER_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getCv(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        return notFound().build();
    }

    @PUT
    @Path(ApiPaths.DECODER_CV_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response updateCv(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv, @QueryParam(ApiNames.WERT) Integer wert) {
        return notFound().build();
    }

    @GET
    @Path(ApiPaths.DECODER_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getFunktion(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        return notFound().build();
    }

    @PUT
    @Path(ApiPaths.DECODER_FUNKTION_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response updateFunktion(@PathParam(ApiPaths.NAME_PARAM_NAME) String decoderId, @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion, @QueryParam(ApiNames.DESCRIPTION) String descirption) {
        return notFound().build();
    }

    protected IDecoder findDecoder(String decoderId, boolean eager) throws Exception {
        return getPersister().findByKey(decoderId, true);
    }

    protected IDecoderAdress findDecoderAdress(IDecoder decoder, Integer reihe, boolean eager) throws Exception {
        return null;
    }

    protected IDecoderCV findDecoderCV(IDecoderTyp decoderTyp, Integer cv, boolean eager) throws Exception {
        return null;
    }

    protected IDecoderFunktion findDecoderFunktion(IDecoderTyp decoderTyp, Integer reihe, String funktion) throws Exception {
        return null;
    }
}
