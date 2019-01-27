package com.linepro.modellbahn.rest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.model.refs.IDescribedEnum;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.json.serialization.DescribedEnumWrapper;
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

    protected final Logger logger;
    
    public EnumsService() {
        super();

        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @GET
    @Path(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible AdressTyp values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getAdressTyp() {
        logGet(ApiPaths.ENUMS_ADRESS_TYP_PATH);

        return ok(getEnumValues(AdressTyp.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible AnderungTyp values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getAnderungTyp() {
        logGet(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH);

        return ok(getEnumValues(AnderungsTyp.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_STECKER_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Stecker values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getConnector() {
        logGet(ApiPaths.ENUMS_STECKER_PATH);

        return ok(getEnumValues(Stecker.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Konfiguration values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getKonfiguration() {
        logGet(ApiPaths.ENUMS_KONFIGURATION_PATH);

        return ok(getEnumValues(Konfiguration.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Status values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getStatus() {
        logGet(ApiPaths.ENUMS_STATUS_PATH);

        return ok(getEnumValues(Status.values())).build();
    }

    @GET
    @Path(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs= IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible LeistungsUbertragung values", response = IDescribedEnum.class, responseContainer = "List")
    public Response getLeistungsUbertragung() {
        logGet(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH);

        return ok(getEnumValues(LeistungsUbertragung.values())).build();
    }

    private List<IDescribedEnum> getEnumValues(IDescribedEnum[] values) {
        return Stream.of(values).map(DescribedEnumWrapper::new).collect(Collectors.toList());
    }
}
