package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.ok;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.enums.DescribedEnum;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.EnumsService;
import com.linepro.modellbahn.service.base.AbstractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ENUMS)
@RestController
@RequestMapping(ApiPaths.ENUMS)
public class EnumsController extends AbstractService {

    private final EnumsService service;

    @Autowired
    public EnumsController(EnumsService service) {
        this.service = service;
    }

    @GetMapping(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(
            value = "Gets all possible AdressTyp values",
            response = DescribedEnum.class,
            responseContainer = "List")
    public ResponseEntity<?> getAdressTyp() {
        logGet(ApiPaths.ENUMS_ADRESS_TYP_PATH);

        return ok(service.getEnumValues(AdressTyp.class));
    }

    @GetMapping(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(
            value = "Gets all possible AnderungTyp values",
            response = DescribedEnum.class,
            responseContainer = "List")
    public ResponseEntity<?> getAnderungTyp() {
        logGet(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH);

        return ok(service.getEnumValues(AnderungsTyp.class));
    }

    @GetMapping(ApiPaths.ENUMS_STECKER_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(
            value = "Gets all possible Stecker values",
            response = DescribedEnum.class,
            responseContainer = "List")
    public ResponseEntity<?> getConnector() {
        logGet(ApiPaths.ENUMS_STECKER_PATH);

        return ok(service.getEnumValues(Stecker.class));
    }

    @GetMapping(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(
            value = "Gets all possible Konfiguration values",
            response = DescribedEnum.class,
            responseContainer = "List")
    public ResponseEntity<?> getKonfiguration() {
        logGet(ApiPaths.ENUMS_KONFIGURATION_PATH);

        return ok(service.getEnumValues(Konfiguration.class));
    }

    @GetMapping(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Gets all possible Status values", response = DescribedEnum.class, responseContainer = "List")
    public ResponseEntity<?> getStatus() {
        logGet(ApiPaths.ENUMS_STATUS_PATH);

        return ok(service.getEnumValues(Status.class));
    }

    @GetMapping(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @ApiOperation(
            value = "Gets all possible LeistungsUbertragung values",
            response = DescribedEnum.class,
            responseContainer = "List")
    public ResponseEntity<?> getLeistungsUbertragung() {
        logGet(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH);

        return ok(service.getEnumValues(LeistungsUbertragung.class));
    }
}
