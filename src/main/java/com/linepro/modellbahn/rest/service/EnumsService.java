package com.linepro.modellbahn.rest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ENUMS)
@RestController
@RequestMapping(ApiPaths.ENUMS)
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

    @GetMapping(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible AdressTyp values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getAdressTyp() {
        logGet(ApiPaths.ENUMS_ADRESS_TYP_PATH);

        return ok(getEnumValues(AdressTyp.values()));
    }

    @GetMapping(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible AnderungTyp values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getAnderungTyp() {
        logGet(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH);

        return ok(getEnumValues(AnderungsTyp.values()));
    }

    @GetMapping(ApiPaths.ENUMS_STECKER_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Stecker values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getConnector() {
        logGet(ApiPaths.ENUMS_STECKER_PATH);

        return ok(getEnumValues(Stecker.values()));
    }

    @GetMapping(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Konfiguration values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getKonfiguration() {
        logGet(ApiPaths.ENUMS_KONFIGURATION_PATH);

        return ok(getEnumValues(Konfiguration.values()));
    }

    @GetMapping(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Status values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getStatus() {
        logGet(ApiPaths.ENUMS_STATUS_PATH);

        return ok(getEnumValues(Status.values()));
    }

    @GetMapping(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = IDescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible LeistungsUbertragung values", response = IDescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getLeistungsUbertragung() {
        logGet(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH);

        return ok(getEnumValues(LeistungsUbertragung.values()));
    }

    private List<IDescribedEnum> getEnumValues(IDescribedEnum[] values) {
        return Stream.of(values).map(DescribedEnumWrapper::new).collect(Collectors.toList());
    }
}
