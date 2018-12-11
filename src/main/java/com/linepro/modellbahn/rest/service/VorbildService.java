package com.linepro.modellbahn.rest.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.impl.Artikel;
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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * VorbildService. CRUD service for Vorbild
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.VORBILD)
public class VorbildService extends AbstractItemService<NameKey, Vorbild> {

    public VorbildService() {
        super(Vorbild.class);
    }

    @JsonCreator
    public Vorbild create(@JsonProperty(value=ApiNames.ID, required=false) Long id,
            @JsonProperty(value=ApiNames.GATTUNG, required=false) IGattung gattung,
            @JsonProperty(value=ApiNames.UNTER_KATEGORIE, required=true) IUnterKategorie unterKategorie,
            @JsonProperty(value=ApiNames.BAHNVERWALTUNG, required=false) IBahnverwaltung bahnverwaltung,
            @JsonProperty(value=ApiNames.HERSTELLER, required=false) String hersteller,
            @JsonProperty(value=ApiNames.BAUZEIT, required=false) Date bauzeit,
            @JsonProperty(value=ApiNames.ANZAHL, required=false) Integer anzahl,
            @JsonProperty(value=ApiNames.BETREIBSNUMMER, required=false) String betreibsNummer,
            @JsonProperty(value=ApiNames.ANTRIEB, required=false) IAntrieb antrieb,
            @JsonProperty(value=ApiNames.ACHSFOLG, required=false) IAchsfolg achsfolg,
            @JsonProperty(value=ApiNames.ANFAHRZUGKRAFT, required=false) BigDecimal anfahrzugkraft,
            @JsonProperty(value=ApiNames.LEISTUNG, required=false) BigDecimal leistung,
            @JsonProperty(value=ApiNames.DIENSTGEWICHT, required=false) BigDecimal dienstgewicht,
            @JsonProperty(value=ApiNames.GESCHWINDIGKEIT, required=false) Integer geschwindigkeit,
            @JsonProperty(value=ApiNames.LANGE, required=false) BigDecimal lange,
            @JsonProperty(value=ApiNames.AUSSERDIENST, required=false) Date ausserdienst,
            @JsonProperty(value=ApiNames.DMTREIBRAD, required=false) BigDecimal dmTreibrad,
            @JsonProperty(value=ApiNames.DMLAUFRADVORN, required=false) BigDecimal dmLaufradVorn,
            @JsonProperty(value=ApiNames.DMLAUFRADHINTEN, required=false) BigDecimal dmLaufradHinten,
            @JsonProperty(value=ApiNames.ZYLINDER, required=false) Integer zylinder,
            @JsonProperty(value=ApiNames.DMZYLINDER, required=false) BigDecimal dmZylinder,
            @JsonProperty(value=ApiNames.KOLBENHUB, required=false) BigDecimal kolbenhub,
            @JsonProperty(value=ApiNames.KESSELUEBERDRUCK, required=false) BigDecimal kesselueberdruck,
            @JsonProperty(value=ApiNames.ROSTFLAECHE, required=false) BigDecimal rostflaeche,
            @JsonProperty(value=ApiNames.UEBERHITZERFLAECHE, required=false) BigDecimal ueberhitzerflaeche,
            @JsonProperty(value=ApiNames.WASSERVORRAT, required=false) BigDecimal wasservorrat,
            @JsonProperty(value=ApiNames.VERDAMPFUNG, required=false) BigDecimal verdampfung,
            @JsonProperty(value=ApiNames.FAHRMOTOREN, required=false) Integer fahrmotoren,
            @JsonProperty(value=ApiNames.MOTORBAUART, required=false) String motorbauart,
            @JsonProperty(value=ApiNames.LEISTUNGSUEBERTRAGUNG, required=false) BigDecimal leistungsuebertragung,
            @JsonProperty(value=ApiNames.REICHWEITE, required=false) BigDecimal reichweite,
            @JsonProperty(value=ApiNames.KAPAZITAT, required=false) BigDecimal kapazitaet,
            @JsonProperty(value=ApiNames.KLASSE, required=false) Integer klasse,
            @JsonProperty(value=ApiNames.SITZPLATZEKL1, required=false) Integer sitzPlatzeKL1,
            @JsonProperty(value=ApiNames.SITZPLATZEKL2, required=false) Integer sitzPlatzeKL2,
            @JsonProperty(value=ApiNames.SITZPLATZEKL3, required=false) Integer sitzPlatzeKL3,
            @JsonProperty(value=ApiNames.SITZPLATZEKL4, required=false) Integer sitzPlatzeKL4,
            @JsonProperty(value=ApiNames.AUFBAU, required=false) String aufbauten,
            @JsonProperty(value=ApiNames.TRIEBZUGANZEIGEN, required=false) Boolean triebzugAnzeigen,
            @JsonProperty(value=ApiNames.TRIEBKOEPFE, required=false) Integer triebkoepfe,
            @JsonProperty(value=ApiNames.MITTELWAGEN, required=false) Integer mittelwagen,
            @JsonProperty(value=ApiNames.SITZPLATZETZKL1, required=false) Integer sitzPlatzeTZKL1,
            @JsonProperty(value=ApiNames.SITZPLATZETZKL2, required=false) Integer sitzPlatzeTzKL2,
            @JsonProperty(value=ApiNames.DREHGESTELLBAUART, required=false) String drehgestellbauart,
            @JsonProperty(value=ApiNames.ANMERKUNG, required=false) String anmerkung,
            @JsonProperty(value=ApiNames.ABBILDUNG, required=false) String abbildungStr,
            @JsonProperty(value=ApiNames.DELETED, required=false) Boolean deleted) {
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
    public Response get(@PathParam(ApiPaths.NAME_PARAM_NAME) String name) {
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
    public Response add(Vorbild entity) {
        return super.add(entity);
    }

    @PUT
    @Path(ApiPaths.NAME_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response update(@PathParam(ApiPaths.NAME_PARAM_NAME) String name, Vorbild entity) {
        return super.update(name, entity);
    }

    @DELETE
    @Path(ApiPaths.NAME_PART)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response delete(@PathParam(ApiPaths.ID_PARAM_NAME) String name) {
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

            IArtikel vorbild = findArtikel(name, false);

            if (vorbild != null) {
                java.nio.file.Path file = handler.upload(ApiNames.ARTIKEL, new String[] { name }, fileDetail, fileData);

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
