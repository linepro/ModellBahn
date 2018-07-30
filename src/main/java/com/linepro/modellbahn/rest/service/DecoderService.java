package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import com.linepro.modellbahn.model.IDecoder;
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

    /**
     * Instantiates a new decoder typ service.
     */
    public DecoderService() {
        super(Decoder.class);
    }

    /**
     * Creates the.
     *
     * @param id
     *            the id
     * @param name
     *            the name
     * @param bezeichnung
     *            the bezeichnung
     * @param deleted
     *            the deleted
     * @param decoderTyp
     * @param fahrstufe
     * @return the e
     * @throws Exception
     *             the exception
     */
    @JsonCreator
    public Decoder create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.DECODER_TYP, required = false) DecoderTyp decoderTyp,
            @JsonProperty(value = ApiPaths.PROTOKOLL, required = false) Protokoll protokoll,
            @JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.FAHRSTUFE, required = false) Integer fahrstufe,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        Decoder entity = new Decoder(id, decoderTyp, protokoll, decoderId, bezeichnung, fahrstufe, deleted);

        info("create " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderAdress createAdress(@JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.ADRESS_TYP, required = false) String adressTypStr,
            @JsonProperty(value = ApiNames.ADRESS, required = false) Integer adress) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);
        AdressTyp adressTyp = AdressTyp.valueOf(adressTypStr);

        DecoderAdress entity = new DecoderAdress(decoder, adressTyp, adress);

        info("create " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderCV createCV(@JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiNames.CV, required = false) Integer cvValue,
            @JsonProperty(value = ApiNames.WERT, required = false) Integer wert) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);

        IDecoderTypCV decoderTypCV = findDecoderTypeCV(decoder, cvValue, true);

        DecoderCV entity = new DecoderCV(decoder, decoderTypCV, wert);

        info("create " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderFunktion createFunktion(@JsonProperty(value = ApiNames.DECODER_ID, required = false) String decoderId,
            @JsonProperty(value = ApiPaths.NAME_PARAM_NAME, required = false) String funktion,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung) throws Exception {
        IDecoder decoder = findDecoder(decoderId, false);

        IDecoderTypFunktion decoderTypFunktion = findDecoderTypeFunktion(decoder, funktion);

        DecoderFunktion entity = new DecoderFunktion(decoder, decoderTypFunktion, bezeichnung);

        info("create " + entity);

        return entity;
    }

    /**
     * Gets the.
     *
     * @param name
     *            the name
     * @return the response
     */
    @GET
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    public Response search(@Context UriInfo uriInfo) {
        return super.search(uriInfo);
    }

    /**
     * Update.
     *
     * @param name
     *            the name
     * @param entity
     *            the entity
     * @return the response
     */
    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Decoder entity) {
        return super.update(name, entity);
    }

    /**
     * Delete.
     *
     * @param name
     *            the name
     * @return the response
     */
    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    protected IDecoder findDecoder(String decoderId, boolean eager) throws Exception {
        return getPersister().findByKey(decoderId, true);
    }

    protected IDecoderTypCV findDecoderTypeCV(IDecoder decoder, Integer cvValue, boolean eager) throws Exception {
        return null;
    }

    protected IDecoderTypFunktion findDecoderTypeFunktion(IDecoder decoder, String funktion) throws Exception {
        return null;
    }
}
