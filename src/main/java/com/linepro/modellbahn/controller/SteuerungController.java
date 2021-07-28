package com.linepro.modellbahn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.model.SteuerungModel.PagedSteuerungModel;
import com.linepro.modellbahn.request.SteuerungRequest;
import com.linepro.modellbahn.service.criterion.NamedCriterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;
import com.linepro.modellbahn.service.impl.SteuerungService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * SteuerungService. CRUD service for Steuerung
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.STEUERUNG)
@RestController("Steuerung")
@ExposesResourceFor(SteuerungModel.class)
@SecurityRequirement(name = "BasicAuth")
public class SteuerungController extends NamedItemController<SteuerungModel, SteuerungRequest> {

    private final SteuerungService service;

    @Autowired
    public SteuerungController(SteuerungService service) {
        super(service);

        this.service = service;
    }

    @Override
    @GetMapping(path = ApiPaths.GET_STEUERUNG)
    @Operation(summary = "Finds an Steuerung by name", description = "Finds a control method", operationId = "get", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "200", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = SteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return super.get(name);
    }

    @GetMapping(path = ApiPaths.SEARCH_STEUERUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds Steuerungen by example", description = "Finds UIC axle configurations", operationId = "find", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "200", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = PagedSteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(NamedCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_STEUERUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Add a new Steuerung", description = "Add a new UIC axle configuration", operationId = "add", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "201", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = SteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody SteuerungRequest request) {
        return super.add(request);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_STEUERUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Steuerung by name", description = "Update a control method", operationId = "update", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = SteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody SteuerungRequest request) {
        return super.update(name, request);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_STEUERUNG)
    @Operation(summary = "Deletes an Steuerung by name", description = "Delete a control method", operationId = "delete", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name) {
        return super.delete(name);
    }

    @PutMapping(path = ApiPaths.ADD_STEUERUNG_ABBILDUNG, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Add an Steuerung picture", description = "Adds or updates the picture of a named Steuerung", operationId = "update", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = SteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Steuerung not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String name, @RequestParam(ApiNames.ABBILDUNG) MultipartFile multipart) {
        return updated(service.updateAbbildung(name, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_STEUERUNG_ABBILDUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Delete an Steuerung picture", description = "Deletes the picture of a named Steuerung", operationId = "update", tags = {
                    ApiNames.STEUERUNG
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = SteuerungModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Steuerung not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String name) {
        return updated(service.deleteAbbildung(name));
    }
}
