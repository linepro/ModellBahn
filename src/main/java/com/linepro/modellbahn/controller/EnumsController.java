package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.ok;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.AnderungsTyp;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.DescribedEnum;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.LeistungsUbertragung;
import com.linepro.modellbahn.model.enums.Status;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.service.impl.EnumsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.ENUMS)
@RestController("EnumsController")
public class EnumsController {

    private final EnumsService service;

    @Autowired
    public EnumsController(EnumsService service) {
        this.service = service;
    }

    @GetMapping(path = ApiPaths.ENUMS_ADRESS_TYP_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible AdressTyp values")
    public ResponseEntity<?> getAdressTyp() {
        return ok(service.getEnumValues(AdressTyp.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_ANDERUNGS_TYP_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible AnderungTyp values")
    public ResponseEntity<?> getAnderungTyp() {
        return ok(service.getEnumValues(AnderungsTyp.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_DECODER_STATUS_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible DecoderStatus values")
    public ResponseEntity<?> getDecoderStatus() {
        return ok(service.getEnumValues(DecoderStatus.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_STECKER_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible Stecker values")
    public ResponseEntity<?> getConnector() {
        return ok(service.getEnumValues(Stecker.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_KONFIGURATION_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible Konfiguration values")
    public ResponseEntity<?> getKonfiguration() {
        return ok(service.getEnumValues(Konfiguration.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_STATUS_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible Status values")
    public ResponseEntity<?> getStatus() {
        return ok(service.getEnumValues(Status.values()));
    }

    @GetMapping(path = ApiPaths.ENUMS_LEISTUNGS_UBERTRAGUNG_PATH, produces = MediaType.APPLICATION_JSON)
    @JsonSerialize(contentAs = DescribedEnum.class)
    @Operation(summary = "Gets all possible LeistungsUbertragung values")
    public ResponseEntity<?> getLeistungsUbertragung() {
        return ok(service.getEnumValues(LeistungsUbertragung.values()));
    }
}
