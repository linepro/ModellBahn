package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.ok;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.enums.AdressTypModel.PagedAdressTypModel;
import com.linepro.modellbahn.model.enums.AnderungsTypModel.PagedAnderungsTypModel;
import com.linepro.modellbahn.model.enums.DecoderStatusModel.PagedDecoderStatusModel;
import com.linepro.modellbahn.model.enums.KonfigurationModel.PagedKonfigurationModel;
import com.linepro.modellbahn.model.enums.LandModel.PagedLandModel;
import com.linepro.modellbahn.model.enums.LeistungsUbertragungModel.PagedLeistungsUbertragungModel;
import com.linepro.modellbahn.model.enums.StatusModel.PagedStatusModel;
import com.linepro.modellbahn.model.enums.SteckerModel.PagedSteckerModel;
import com.linepro.modellbahn.model.enums.WahrungModel.PagedWahrungModel;
import com.linepro.modellbahn.service.impl.EnumsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.ENUMS)
@RestController("Enums")
@SecurityRequirement(name = "BasicAuth")
public class EnumsController {

    private final EnumsService service;

    @Autowired
    public EnumsController(EnumsService service) {
        this.service = service;
    }

    @GetMapping(path = ApiPaths.ENUMS_ADRESS_TYP_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible AdressTyp values", description = "Gets all AdressTypen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.ADRESS_TYP }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedAdressTypModel.class)) }),
                })
    public ResponseEntity<?> getAdressTyp() {
        return found(service.getAdressTyp());
    }

    @GetMapping(path = ApiPaths.ENUMS_ANDERUNGS_TYP_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible AnderungTyp values", description = "Gets all AnderungTypen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.ANDERUNGS_TYP }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedAnderungsTypModel.class)) }),
                })
    public ResponseEntity<?> getAnderungTyp() {
        return found(service.getAnderungTyp());
    }

    @GetMapping(path = ApiPaths.ENUMS_DECODER_STATUS_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible DecoderStatus values", description = "Gets all DecoderStatusen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STATUS }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedDecoderStatusModel.class)) }),
                })
    public ResponseEntity<?> getDecoderStatus() {
        return found(service.getDecoderStatus());
    }

    @GetMapping(path = ApiPaths.ENUMS_KONFIGURATION_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible Konfiguration values", description = "Gets all Konfigurationen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.KONFIGURATION }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedKonfigurationModel.class)) }),
                })
    public ResponseEntity<?> getKonfiguration() {
        return found(service.getKonfiguration());
    }

    @GetMapping(path = ApiPaths.ENUMS_LAND_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible Land values", description = "Gets all Lander", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.LAND }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedLandModel.class)) }),
                })
    public ResponseEntity<?> getLand() {
        return found(service.getLand());
    }

    @GetMapping(path = ApiPaths.ENUMS_LEISTUNGSUBERTRAGUNG_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible LeistungsUbertragung values", description = "Gets all LeistungsUbertragungs", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.LEISTUNGSUBERTRAGUNG }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedLeistungsUbertragungModel.class)) }),
                })
    public ResponseEntity<?> getLeistungsUbertragung() {
        return found(service.getLeistungsUbertragung());
    }

    @GetMapping(path = ApiPaths.ENUMS_STATUS_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible Status values", description = "Gets all Statusen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STATUS }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedStatusModel.class)) }),
                })
    public ResponseEntity<?> getStatus() {
        return found(service.getStatus());
    }

    @GetMapping(path = ApiPaths.ENUMS_STECKER_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible Stecker values", description = "Gets all Steckern", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STECKER }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedSteckerModel.class)) }),
                })
    public ResponseEntity<?> getConnector() {
        return found(service.getStecker());
    }

    @GetMapping(path = ApiPaths.ENUMS_WAHRUNG_PATH, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Gets all possible Wahrung values", description = "Gets all Wahrungen", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.WAHRUNG }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedWahrungModel.class)) }),
                })
    public ResponseEntity<?> getWahrung() {
        return found(service.getWahrung());
    }

    public <I> ResponseEntity<?> found(Page<I> page) {
        PagedResourcesAssembler<I> assembler = new PagedResourcesAssembler<I>(null, null);

        return ok().header(HttpHeaders.CONTENT_TYPE, ApiPaths.APPLICATION_HAL_JSON)
                   .body(assembler.toModel(page, it -> (RepresentationModel<?>) it));
    }
}
