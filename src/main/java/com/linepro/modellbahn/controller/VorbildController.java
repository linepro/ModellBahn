package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.model.VorbildModel.PagedVorbildModel;
import com.linepro.modellbahn.request.VorbildRequest;
import com.linepro.modellbahn.service.criterion.PageCriteria;
import com.linepro.modellbahn.service.criterion.VorbildCriterion;
import com.linepro.modellbahn.service.impl.VorbildService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * VorbildService. CRUD service for Vorbild
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.VORBILD)
@RestController("Vorbild")
@ExposesResourceFor(VorbildModel.class)
@SecurityRequirement(name = "BasicAuth")
public class VorbildController extends AbstractItemController<VorbildModel, VorbildRequest> {

	private final VorbildService service;

    @Autowired
    public VorbildController(VorbildService service) {
        super(service);

        this.service = service;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static VorbildModel create() {
        return new VorbildModel();
    }

    @GetMapping(path = ApiPaths.GET_VORBILD, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds an Vorbild by name", description = "Finds a prototype", operationId = "get", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String gattung) {
        return of(service.get(gattung));
    }

    @GetMapping(path = ApiPaths.SEARCH_VORBILD, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds Vorbilder by example", description = "Finds prototypes", operationId = "find", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedVorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(VorbildCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_VORBILD, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Add a new Vorbild", description = "Add a new UIC axle configuration", operationId = "add", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody VorbildRequest request) {
        return super.add(request);
    }

    @PutMapping(path = ApiPaths.UPDATE_VORBILD, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Vorbild by name", description = "Update a prototyp", operationId = "update", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String gattung, @RequestBody VorbildRequest request) {
        return updated(service.update(gattung, request));
    }

    @DeleteMapping(path = ApiPaths.DELETE_VORBILD)
    @Operation(summary = "Deletes an Vorbild by name", description = "Delete a prototyp", operationId = "delete", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
   public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String gattung) {
        return deleted(service.delete(gattung));
    }

    @PutMapping(path = ApiPaths.ADD_VORBILD_ABBILDUNG, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Vorbild by name", description = "Update a prototype", operationId = "update", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String gattung, @RequestParam(ApiNames.ABBILDUNG) MultipartFile multipart) {
        return updated(service.updateAbbildung(gattung, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_VORBILD_ABBILDUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Delete an Vorbild picture", description = "Deletes the picture of a named Vorbild", operationId = "delete", tags = { ApiNames.VORBILD }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String gattung) {
        return updated(service.deleteAbbildung(gattung));
    }
}
