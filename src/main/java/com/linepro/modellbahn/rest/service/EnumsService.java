package com.linepro.modellbahn.rest.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.util.Connector;
import com.linepro.modellbahn.model.util.Konfiguration;
import com.linepro.modellbahn.model.util.LeistungsUbertragung;
import com.linepro.modellbahn.model.util.Status;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCV and DecoderTypFunktion
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ENUMS)
@Path(ApiPaths.ENUMS)
public class EnumsService extends AbstractService {

    public EnumsService() {
    }

    @GET
    @Path(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible AdressTyp values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getAdressTyp() {
        return ok(Arrays.asList(AdressTyp.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_STECKER_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Connector values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getConnector() {
        return ok(Arrays.asList(Connector.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Konfiguration values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getKonfiguration() {
        return ok(Arrays.asList(Konfiguration.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Status values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getStatus() {
        return ok(Arrays.asList(Status.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible LeistungsUbertragung values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getLeistungsUbertragung() {
        return ok(Arrays.asList(LeistungsUbertragung.values())).build();
    }
}
