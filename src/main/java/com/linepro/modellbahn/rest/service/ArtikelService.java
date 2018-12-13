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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.model.util.Status;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
import com.linepro.modellbahn.rest.util.AcceptableMediaTypes;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.FileUploadHandler;
import com.linepro.modellbahn.rest.util.IFileUploadHandler;
import com.linepro.modellbahn.util.StaticContentFinder;

/**
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.ARTIKEL)
public class ArtikelService extends AbstractItemService<NameKey, Artikel> {

    public ArtikelService() {
        super(Artikel.class);
    }

    @JsonCreator
    public Artikel create(@JsonProperty(value = ApiNames.ID, required = false) Long id,
            @JsonProperty(value = ApiNames.HERSTELLER, required = false) String herstellerStr,
            @JsonProperty(value = ApiNames.BESTELL_NR, required = false) String bestellNr,
            @JsonProperty(value = ApiNames.KAUFDATUM, required = false) Date kaufdatum,
            @JsonProperty(value = ApiNames.WAHRUNG, required = false) String wahrungStr,
            @JsonProperty(value = ApiNames.PREIS, required = false) BigDecimal preis,
            @JsonProperty(value = ApiNames.STUCK, required = false) Integer stuck,
            @JsonProperty(value = ApiNames.STEUERUNG, required = false) String steuerungStr,
            @JsonProperty(value = ApiNames.MOTOR_TYP, required = false) String motorTypStr,
            @JsonProperty(value = ApiNames.LICHT, required = false) String lichtStr,
            @JsonProperty(value = ApiNames.KUPPLUNG, required = false) String kupplungStr,
            @JsonProperty(value = ApiNames.DECODER, required = false) String decoderId,
            @JsonProperty(value = ApiNames.NAMEN, required = false) String artikelNr,
            @JsonProperty(value = ApiNames.BEZEICHNUNG, required = false) String bezeichnung,
            @JsonProperty(value = ApiNames.ANMERKUNG, required = false) String anmerkung,
            @JsonProperty(value = ApiNames.BELADUNG, required = false) String beladung,
            @JsonProperty(value=ApiNames.ABBILDUNG, required=false) String abbildungStr,
            @JsonProperty(value = ApiNames.STATUS, required = false) String statusStr,
            @JsonProperty(value = ApiNames.DELETED, required = false) Boolean deleted) throws Exception {
        IProdukt produkt = findProdukt(herstellerStr, bestellNr, false);
        IWahrung wahrung = findWahrung(wahrungStr, false);
        ISteuerung steuerung = findSteuerung(steuerungStr, false);
        IMotorTyp motorTyp = findMotorTyp(motorTypStr, false);
        ILicht licht = findLicht(lichtStr, false);
        IKupplung kupplung = findKupplung(kupplungStr, false);
        IDecoder decoder = findDecoder(decoderId, false);
        Status status = Status.valueOf(statusStr);
        
        Artikel entity = new Artikel(id, produkt, kaufdatum, wahrung, preis, stuck,
                steuerung, motorTyp, licht, kupplung, decoder,
                artikelNr, bezeichnung, anmerkung,
                beladung, status, deleted);

        debug("created: " + entity);

       return entity;
    }

    @GET
    @Path(ApiPaths.ID_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response get(@PathParam(ApiPaths.ID_PARAM_NAME) Long name) {
        return super.get(name);
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
    public Response add(Artikel entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.ID_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.ID_PARAM_NAME) Long name, Artikel entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.ID_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.ID_PARAM_NAME) Long name) {
        return super.delete(name);
    }

    @PUT
    @Path(ApiPaths.ABBILDUNG_PART)
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response updateAbbildung(@PathParam(ApiPaths.NAME_PARAM_NAME) String name,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DETAIL) FormDataContentDisposition fileDetail,
                                    @FormDataParam(ApiPaths.MULTIPART_FILE_DATA) InputStream fileData) {
        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(fileDetail, fileData, AcceptableMediaTypes.IMAGES)) {
                return getResponse(badRequest(null, "Invalid file '" + fileDetail.getFileName() + "'"));
            }

            IArtikel artikel = findArtikel(name, false);

            if (artikel != null) {
                java.nio.file.Path file = handler.upload(ApiNames.ARTIKEL, new String[] { name }, fileDetail, fileData);

                artikel.setAbbildung(file);

                getPersister().update((Artikel) artikel);

                return getResponse(ok(artikel));
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
    public Response deleteAbbildung(@PathParam(ApiPaths.ID_PARAM_NAME) String name) {
        try {
            IArtikel artikel = findArtikel(name, false);

            if (artikel != null && artikel.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(artikel.getAbbildung());

                artikel.setAbbildung(null);

                getPersister().update((Artikel) artikel);

                return getResponse(ok(artikel));
            }
        } catch (Exception e) {
            return getResponse(serverError(e));
        }

        return getResponse(notFound());
    }
}
