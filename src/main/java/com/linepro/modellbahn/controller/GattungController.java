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
import org.springframework.web.bind.annotation.RestController;

import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.model.GattungModel.PagedGattungModel;
import com.linepro.modellbahn.request.GattungRequest;
import com.linepro.modellbahn.service.criterion.NamedCriterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;
import com.linepro.modellbahn.service.impl.GattungService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * GattungService. CRUD service for Gattung
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.GATTUNG)
@RestController("Gattung")
@ExposesResourceFor(GattungModel.class)
@SecurityRequirement(name = "BasicAuth")
public class GattungController extends NamedItemController<GattungModel, GattungRequest> {

    @Autowired
    public GattungController(GattungService service) {
        super(service);
    }

    @Override
    @GetMapping(path = ApiPaths.GET_GATTUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds an Gattung by name", description = "Finds a vehicle code", operationId = "get", tags = { ApiNames.GATTUNG }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GattungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return super.get(name);
    }

    @GetMapping(path = ApiPaths.SEARCH_GATTUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds Gattungen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { ApiNames.GATTUNG }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedGattungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(NamedCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_GATTUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Add a new Gattung", description = "Add a new UIC axle configuration", operationId = "add", tags = { ApiNames.GATTUNG }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GattungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody GattungRequest request) {
        return super.add(request);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_GATTUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Gattung by name", description = "Update a vehicle code", operationId = "update", tags = { ApiNames.GATTUNG }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GattungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody GattungRequest request) {
        return super.update(name, request);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_GATTUNG)
    @Operation(summary = "Deletes an Gattung by name", description = "Delete a vehicle code", operationId = "delete", tags = { ApiNames.GATTUNG }, responses = {
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
}
