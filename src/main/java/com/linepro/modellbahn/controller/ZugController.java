package com.linepro.modellbahn.controller;

import java.util.Optional;

import javax.ws.rs.core.MediaType;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.service.impl.ZugService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.ZUG)
@RestController("ZugController")
@ExposesResourceFor(ZugModel.class)
public class ZugController extends NamedItemController<ZugModel> {

    private final ZugService service;

    public ZugController(ZugService service) {
        super(service);

        this.service = service;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static ZugModel create() {
        return new ZugModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static ZugConsistModel createZugConsist() {
        return new ZugConsistModel();
    }

    @Override
    @GetMapping(path = ApiPaths.GET_ZUG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Zug by name", description = "Finds a train", operationId = "get", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
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
    @GetMapping(path = ApiPaths.SEARCH_ZUG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Zugen by example", description = "Finds train configurations", operationId = "find", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ZugModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<ZugModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_ZUG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Zug", description = "Update a train", operationId = "update", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody ZugModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_ZUG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Zug by name", description = "Update a train", operationId = "update", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody ZugModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_ZUG)
    @Operation(summary = "Deletes an Zug by name", description = "Delete a train", operationId = "delete", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name) {
        return super.delete(name);
    }

    @PostMapping(path = ApiPaths.ADD_CONSIST, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds a vehicle to a train", description = "", operationId = "add", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addConsist(@PathVariable(ApiNames.NAMEN) String zugStr, @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        return added(service.addConsist(zugStr, artikelId));
    }

    @PutMapping(path = ApiPaths.UPDATE_CONSIST, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a vehicle in a train", description = "", operationId = "updates", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateConsist(@PathVariable(ApiNames.NAMEN) String zugStr, @PathVariable(ApiNames.POSITION) Integer position, @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        return updated(service.updateConsist(zugStr, position, artikelId));
    }

    @DeleteMapping(path = ApiPaths.DELETE_CONSIST)
    @Operation(summary = "Removes a vehicle from a train", description = "", operationId = "delete", tags = { ApiNames.ZUG })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteConsist(@PathVariable(ApiNames.NAMEN) String zugStr, @PathVariable(ApiNames.POSITION) Integer position) {
        return deleted(service.deleteConsist(zugStr, position));
    }
}
