package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.ok;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.enums.AnderungsTypModel.PagedAnderungsTypModel;
import com.linepro.modellbahn.model.enums.DecoderStatusModel.PagedDecoderStatusModel;
import com.linepro.modellbahn.model.enums.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.KonfigurationModel.PagedKonfigurationModel;
import com.linepro.modellbahn.model.enums.LeistungsUbertragungModel.PagedLeistungsUbertragungModel;
import com.linepro.modellbahn.model.enums.StatusModel.PagedStatusModel;
import com.linepro.modellbahn.model.enums.SteckerModel.PagedSteckerModel;
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

    @GetMapping(path = ApiPaths.ENUMS_ADRESS_TYP_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible AdressTyp values", description = "Gets all AdressTyps", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.ADRESS_TYP }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedSchema.class)) }),
                })
    public ResponseEntity<?> getAdressTyp() {
        return found(service.getAdressTyp());
    }

    @GetMapping(path = ApiPaths.ENUMS_ANDERUNGS_TYP_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible AnderungTyp values", description = "Gets all AnderungTyps", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.ANDERUNGS_TYP }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedAnderungsTypModel.class)) }),
                })
    public ResponseEntity<?> getAnderungTyp() {
        return found(service.getAnderungTyp());
    }

    @GetMapping(path = ApiPaths.ENUMS_DECODER_STATUS_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible DecoderStatus values", description = "Gets all DecoderStatuses", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STATUS }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedDecoderStatusModel.class)) }),
                })
    public ResponseEntity<?> getDecoderStatus() {
        return found(service.getDecoderStatus());
    }

    @GetMapping(path = ApiPaths.ENUMS_STECKER_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible Stecker values", description = "Gets all Steckers", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STECKER }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedSteckerModel.class)) }),
                })
    public ResponseEntity<?> getConnector() {
        return found(service.getStecker());
    }

    @GetMapping(path = ApiPaths.ENUMS_KONFIGURATION_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible Konfiguration values", description = "Gets all Konfigurations", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.KONFIGURATION }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedKonfigurationModel.class)) }),
                })
    public ResponseEntity<?> getKonfiguration() {
        return found(service.getKonfiguration());
    }

    @GetMapping(path = ApiPaths.ENUMS_STATUS_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible Status values", description = "Gets all Statuses", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.STATUS }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedStatusModel.class)) }),
                })
    public ResponseEntity<?> getStatus() {
        return found(service.getStatus());
    }

    @GetMapping(path = ApiPaths.ENUMS_LEISTUNGSUBERTRAGUNG_PATH, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets all possible LeistungsUbertragung values", description = "Gets all LeistungsUbertragungs", operationId = "get", tags = { ApiNames.ENUMS, ApiNames.LEISTUNGSUBERTRAGUNG }, responses = {
                    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedLeistungsUbertragungModel.class)) }),
                })
    public ResponseEntity<?> getLeistungsUbertragung() {
        return found(service.getLeistungsUbertragung());
    }
    
    public <I extends DescribedEnumModel> ResponseEntity<?> found(Page<I> page) {
        PagedResourcesAssembler<I> assembler = new PagedResourcesAssembler<I>(null, null);

        return ok(assembler.toModel(page, it -> (RepresentationModel<?>) it));
    }
}
