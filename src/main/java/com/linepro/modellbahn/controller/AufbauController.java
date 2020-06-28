package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

import java.util.Optional;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
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
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.service.impl.AufbauService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * AufbauService. CRUD service for Aufbau
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.AUFBAU)
@RestController("AufbauController")
@ExposesResourceFor(AufbauModel.class)
public class AufbauController extends NamedItemController<AufbauModel> {

    private final AufbauService service;
    
    @Autowired
    public AufbauController(AufbauService service) {
        super(service);
        
        this.service = service;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static AufbauModel create() {
        return new AufbauModel();
    }
    
    @Override
    @GetMapping(path = ApiPaths.GET_AUFBAU, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Aufbau by name", description = "Finds a costruction style", operationId = "get", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AufbauModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(path = ApiPaths.SEARCH_AUFBAU, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Aufbauen by example", description = "Finds costruction styles", operationId = "get", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AufbauModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<AufbauModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_AUFBAU, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Aufbau", description = "Update a costruction style", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AufbauModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody AufbauModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_AUFBAU, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Aufbau by name", description = "Update a costruction style", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AufbauModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody AufbauModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_AUFBAU)
    @Operation(summary = "Deletes an Aufbau by name", description = "Delete a costruction style", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name) {
        return super.delete(name);
    }

    @PutMapping(path = ApiPaths.ADD_AUFBAU_ABBILDUNG, consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Add an Aufbau picture", description = "Adds or updates the picture of a named Aufbau", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AufbauModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String name, @RequestParam("abbildung") MultipartFile multipart) throws Exception {
        return of(service.updateAbbildung(name, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_AUFBAU_ABBILDUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an Aufbau picture", description = "Deletes the picture of a named Aufbau", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AufbauModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String name) throws Exception {
        return of(service.deleteAbbildung(name));
    }
}
