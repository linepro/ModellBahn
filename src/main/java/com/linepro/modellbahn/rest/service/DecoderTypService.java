package com.linepro.modellbahn.rest.service;

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
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.model.keys.DecoderTypCVKey;
import com.linepro.modellbahn.model.keys.DecoderTypFunktionKey;
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
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCV and DecoderTypFunktion
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.DECODER_TYP)
public class DecoderTypService extends AbstractItemService<ProduktKey, DecoderTyp> {

    protected final IPersister<Protokoll> protokollPersister;

    protected final IPersister<Hersteller> herstellerPersister;

    protected final IPersister<DecoderTypCV> cvPersister;

    protected final IPersister<DecoderTypFunktion> funktionPersister;

    public DecoderTypService() {
        super(DecoderTyp.class);
        
        protokollPersister = StaticPersisterFactory.get().createPersister(Protokoll.class);
        herstellerPersister = StaticPersisterFactory.get().createPersister(Hersteller.class);
        cvPersister = StaticPersisterFactory.get().createPersister(DecoderTypCV.class);
        funktionPersister = StaticPersisterFactory.get().createPersister(DecoderTypFunktion.class);
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
            @JsonProperty(value = ApiNames.HERSTELLER, required = false) String herstellerStr,
            @JsonProperty(value = ApiNames.PROTOKOLL, required = false) String protokollStr,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
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
            @JsonProperty(value = ApiNames.HERSTELLER, required = false) String herstellerStr,
            @JsonProperty(value = ApiNames.BESTELL_NR, required = false) String bestellNr,
            @JsonProperty(value = ApiNames.NAME, required = false) String name,
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
            @JsonProperty(value = ApiNames.HERSTELLER, required = false) String herstellerStr,
            @JsonProperty(value = ApiNames.BESTELL_NR, required = false) String bestellNr,
            @JsonProperty(value = ApiNames.REIHE, required = false) Integer reihe,
            @JsonProperty(value = ApiNames.NAME, required = false) String funktion,
            @JsonProperty(value = ApiNames.DESCRIPTION, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.PROGRAMMABLE, required = false) Boolean programmable,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IDecoderTyp decoderTyp = findDecoderTyp(herstellerStr, bestellNr, false);

        DecoderTypFunktion entity = new DecoderTypFunktion(id, decoderTyp, reihe, funktion, bezeichnung, programmable,
                deleted);

        info("create " + entity);

        return entity;
    }

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

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(DecoderTyp entity) {
        return super.add(entity);
    }

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

    @GET
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        try {
            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            IDecoderTypCV decoderTypCV = findDecoderTypCV(decoderTyp, cv, true);

            if (decoderTypCV != null) {
                return getResponse(ok(), decoderTypCV, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_CV_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTypCV decoderTypCV)
            throws Exception {
        try {
            info("POST " + herstellerStr + "/" + bestellNr + "/" + decoderTypCV);

            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            decoderTyp.addCV(decoderTypCV);

            getPersister().update(decoderTyp);

            return getResponse(created(), decoderTypCV, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv, DecoderTypCV newDecoderTypCV)
            throws Exception {
        try {
            info("PUT " + herstellerStr + "/" + bestellNr + "/" + cv + ": " + newDecoderTypCV);

            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            IDecoderTypCV decoderTypCV = findDecoderTypCV(decoderTyp, cv, true);

            if (decoderTypCV == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            } else if (newDecoderTypCV.getDecoderTyp() == null) {
                newDecoderTypCV.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypCV.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return getResponse(badRequest(null, "You cannot change the decoderTyp for an decoderTypCV, create a new one"));
            }

            decoderTypCV = getCVPersister().update(newDecoderTypCV);

            return getResponse(accepted(), decoderTypCV, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.CV_PARAM_NAME) Integer cv) throws Exception {
        try {
            DecoderTypCV decoderTypCV = (DecoderTypCV) findDecoderTypCV(herstellerStr, bestellNr, cv, true);

            if (decoderTypCV == null) {
                return getResponse(badRequest(null, "DecoderTypCV " + herstellerStr + "/" + bestellNr + "/" + cv + " does not exist"));
            }

            DecoderTyp decoderTyp = (DecoderTyp) decoderTypCV.getDecoderTyp();

            decoderTyp.removeCV(decoderTypCV);

            //getDecoderTypCVPersister().delete(decoderTypCV);

            getPersister().update(decoderTyp);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @GET
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        try {
            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, true);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(decoderTyp, reihe, funktion, true);

            if (decoderTypFunktion != null) {
                return getResponse(ok(), decoderTypFunktion, true, true);
            }

            return getResponse(notFound());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @POST
    @Path(ApiPaths.DECODER_TYP_FUNKTION_ROOT)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response add(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, DecoderTypFunktion decoderTypFunktion)
            throws Exception {
        try {
            info("POST " + herstellerStr + "/" + bestellNr + "/" + decoderTypFunktion);

            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            decoderTyp.addFunktion(decoderTypFunktion);

            getPersister().update(decoderTyp);

            return getResponse(created(), decoderTypFunktion, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @PUT
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion, DecoderTypFunktion newDecoderTypFunktion)
            throws Exception {
        try {
            info("PUT " + herstellerStr + "/" + bestellNr + "/" + reihe + "/" + funktion + ": " + newDecoderTypFunktion);

            DecoderTyp decoderTyp = (DecoderTyp) findDecoderTyp(herstellerStr, bestellNr, false);

            if (decoderTyp == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            }

            IDecoderTypFunktion decoderTypFunktion = findDecoderTypFunktion(decoderTyp, reihe, funktion, true);

            if (decoderTypFunktion == null) {
                return getResponse(badRequest(null, "DecoderTyp " + herstellerStr + "/" + bestellNr + " does not exist"));
            } else if (newDecoderTypFunktion.getDecoderTyp() == null) {
                newDecoderTypFunktion.setDecoderTyp(decoderTyp);
            } else if (!newDecoderTypFunktion.getDecoderTyp().equals(decoderTyp)) {
                // Attempt to change decoderTyp not allowed
                return getResponse(badRequest(null, "You cannot change the decoderTyp for an decoderTypFunktion, create a new one"));
            }

            decoderTypFunktion = getFunktionPersister().update(newDecoderTypFunktion);

            return getResponse(accepted(), decoderTypFunktion, true, true);
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    @DELETE
    @Path(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathParam(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathParam(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathParam(ApiPaths.FUNKTION_PARAM_NAME) String funktion) throws Exception {
        try {
            DecoderTypFunktion decoderTypFunktion = (DecoderTypFunktion) findDecoderTypFunktion(herstellerStr, bestellNr, reihe, funktion, true);

            if (decoderTypFunktion == null) {
                return getResponse(badRequest(null, "DecoderTypFunktion " + herstellerStr + "/" + bestellNr + "/" + reihe + "/" + funktion + " does not exist"));
            }

            DecoderTyp decoderTyp = (DecoderTyp) decoderTypFunktion.getDecoderTyp();

            decoderTyp.removeFunktion(decoderTypFunktion);

            //getDecoderTypFunktionPersister().delete(decoderTypFunktion);

            getPersister().update(decoderTyp);

            return getResponse(noContent());
        } catch (Exception e) {
            return getResponse(serverError(e));
        }
    }

    protected IDecoderTyp findDecoderTyp(String herstellerStr, String bestellNr, boolean eager) throws Exception {
        return getPersister().findByKey(new ProduktKey(findHersteller(herstellerStr), bestellNr), eager);
    }

    protected IDecoderTypCV findDecoderTypCV(String herstellerStr, String bestellNr, Integer cv, boolean eager) throws Exception {
        return findDecoderTypCV(findDecoderTyp(herstellerStr, bestellNr, eager), cv, eager) ;
    }

    protected IDecoderTypCV findDecoderTypCV(IDecoderTyp decoderTyp, Integer cv, boolean eager) throws Exception {
        return getCVPersister().findByKey(new DecoderTypCVKey(decoderTyp, cv), eager);
    }

    protected IDecoderTypFunktion findDecoderTypFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion, boolean eager) throws Exception {
        return findDecoderTypFunktion(findDecoderTyp(herstellerStr, bestellNr, eager), reihe, funktion, eager) ;
    }

    protected IDecoderTypFunktion findDecoderTypFunktion(IDecoderTyp decoderTyp, Integer reihe, String funktion, boolean eager) throws Exception {
        return getFunktionPersister().findByKey(new DecoderTypFunktionKey(decoderTyp, reihe, funktion), eager);
    }

    protected IProtokoll findProtokoll(String protokollStr) throws Exception  {
        return getProtokollPersister().findByKey(new NameKey(protokollStr), false);
    }

    protected IHersteller findHersteller(String herstellerStr) throws Exception  {
        return getHerstellerPersister().findByKey(new NameKey(herstellerStr), false);
    }

    protected IPersister<Protokoll> getProtokollPersister() {
        return protokollPersister;
    }

    protected IPersister<Hersteller> getHerstellerPersister() {
        return herstellerPersister;
    }

    public IPersister<DecoderTypCV> getCVPersister() {
        return cvPersister;
    }

    public IPersister<DecoderTypFunktion> getFunktionPersister() {
        return funktionPersister;
    }
}
