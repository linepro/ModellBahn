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
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.enums.DescribedEnum;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.EnumsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.ENUMS)
@RestController
@RequestMapping(ApiPaths.ENUMS)
public class EnumsController {

    private final EnumsService service;

    @Autowired
    public EnumsController(EnumsService service) {
        this.service = service;
    }

    @GetMapping(ApiPaths.ENUMS_ADRESS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible AdressTyp values")
    public ResponseEntity<?> getAdressTyp() {
        return ok(service.getEnumValues(AdressTyp.class));
    }

    @GetMapping(ApiPaths.ENUMS_ANDERUNGS_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible AnderungTyp values")
    public ResponseEntity<?> getAnderungTyp() {
        return ok(service.getEnumValues(AnderungsTyp.class));
    }

    @GetMapping(ApiPaths.ENUMS_STECKER_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible Stecker values")
    public ResponseEntity<?> getConnector() {
        return ok(service.getEnumValues(Stecker.class));
    }

    @GetMapping(ApiPaths.ENUMS_KONFIGURATION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible Konfiguration values")
    public ResponseEntity<?> getKonfiguration() {
        return ok(service.getEnumValues(Konfiguration.class));
    }

    @GetMapping(ApiPaths.ENUMS_STATUS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible Status values")
    public ResponseEntity<?> getStatus() {
        return ok(service.getEnumValues(Status.class));
    }

    @GetMapping(ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @JsonView(Views.Public.class)
    @Operation(summary = "Gets all possible LeistungsUbertragung values")
    public ResponseEntity<?> getLeistungsUbertragung() {
        return ok(service.getEnumValues(LeistungsUbertragung.class));
    }
}
