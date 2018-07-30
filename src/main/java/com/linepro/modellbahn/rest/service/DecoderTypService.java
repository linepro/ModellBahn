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
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * DecoderTypService. CRUD service for DecoderTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.DECODER_TYP)
public class DecoderTypService extends AbstractItemService<ProduktKey, DecoderTyp> {

    protected final IPersister<Protokoll> protokollPersister;

    protected final IPersister<Hersteller> herstellerPersister;

    /**
     * Instantiates a new decoder typ service.
     */
    public DecoderTypService() {
        super(DecoderTyp.class);
        
        protokollPersister = StaticPersisterFactory.get().createPersister(Protokoll.class);
        herstellerPersister = StaticPersisterFactory.get().createPersister(Hersteller.class);
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
     * @param adressen
     * @param sound
     * @return the e
     * @throws Exception
     *             the exception
     */
    @JsonCreator
    public DecoderTyp create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.HERSTELLER_PARAM_NAME, required = false) String herstellerStr,
            @JsonProperty(value = ApiPaths.PROTOKOLL, required = false) String protokollStr,
            @JsonProperty(value = ApiPaths.NAME_PARAM_NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.ADRESSEN, required = false) Integer adressen,
            @JsonProperty(value = ApiNames.SOUND, required = false) Boolean sound,
            @JsonProperty(value = ApiNames.KONFIGURATION, required = false) String konfigurationStr,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IHersteller hersteller = findHersteller(herstellerStr);
        IProtokoll protokoll = findProtokoll(protokollStr);
        Konfiguration konfiguration = Konfiguration.valueOf(konfigurationStr);

        DecoderTyp entity = new DecoderTyp(id, hersteller, protokoll, name, bezeichnung, adressen, sound, konfiguration,
                deleted);

        info("create " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderTypCV createCV(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.HERSTELLER_PARAM_NAME, required = false) String herstellerStr,
            @JsonProperty(value = ApiPaths.BESTELL_NR_PARAM_NAME, required = false) String bestellNr,
            @JsonProperty(value = ApiPaths.NAME_PARAM_NAME, required = false) String name,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.CV, required = false) Integer cv,
            @JsonProperty(value = ApiNames.MINIMAL, required = false) Integer max,
            @JsonProperty(value = ApiNames.MAXIMAL, required = false) Integer min,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

        DecoderTypCV entity = new DecoderTypCV(id, decoderTyp, cv, bezeichnung, cv, min, max, deleted);

        info("create " + entity);

        return entity;
    }

    @JsonCreator
    public DecoderTypFunktion createFunktion(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiPaths.HERSTELLER_PARAM_NAME, required = false) String herstellerStr,
            @JsonProperty(value = ApiPaths.BESTELL_NR_PARAM_NAME, required = false) String bestellNr,
            @JsonProperty(value = ApiPaths.REIHE_PARAM_NAME, required = false) Integer reihe,
            @JsonProperty(value = ApiPaths.NAME_PARAM_NAME, required = false) String funktion,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.PROGRAMMABLE, required = false) Boolean programmable,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

        DecoderTypFunktion entity = new DecoderTypFunktion(id, decoderTyp, reihe, funktion, bezeichnung, programmable,
                deleted);

        info("create " + entity);

        return entity;
    }

    protected IDecoderTyp findDecoderTyp(String herstellerStr, String bestellNr, boolean eager) throws Exception {
        return getPersister().findByKey(new ProduktKey(findHersteller(herstellerStr), bestellNr), eager);
    }

    protected IProtokoll findProtokoll(String protokollStr) throws Exception  {
        return getProtokollPersister().findByKey(new NameKey(protokollStr), false);
    }

    protected IHersteller findHersteller(String herstellerStr) throws Exception  {
        return getHerstellerPersister().findByKey(new NameKey(herstellerStr), false);
    }

    /**
     * Gets the.
     *
     * @param name
     *            the name
     * @return the response
     */
    @GET
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.get(new ProduktKey(findHersteller(herstellerStr), bestellNr));
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
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTyp entity) {
        try {
            return super.update(new ProduktKey(findHersteller(herstellerStr), bestellNr), entity);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    /**
     * Delete.
     *
     * @param name
     *            the name
     * @return the response
     */
    @DELETE
    @Path(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(new ProduktKey(findHersteller(herstellerStr), bestellNr));
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    protected IPersister<Protokoll> getProtokollPersister() {
        return protokollPersister;
    }

    protected IPersister<Hersteller> getHerstellerPersister() {
        return herstellerPersister;
    }
}
