package com.linepro.modellbahn.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.util.Connector;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.model.util.Status;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractService;
import com.linepro.modellbahn.rest.util.ApiPaths;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCV and DecoderTypFunktion
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.ENUMS)
public class EnumsService extends AbstractService {

    public EnumsService() {
    }

    @GET
    @Path(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getAdressTyp() {
        return ok(getEnumList(AdressTyp.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_CONNECTOR_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getConnector() {
        return ok(getEnumList(Connector.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getKonfiguration() {
        return ok(getEnumList(Konfiguration.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    public Response getStatus() {
        return ok(getEnumList(Status.values())).build();
    }
}
