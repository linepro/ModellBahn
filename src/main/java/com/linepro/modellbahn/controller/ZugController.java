package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.rest.json.Views;
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
@RestController
@RequestMapping(ApiPaths.ZUG)
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
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an Zug by name", description = "Finds a train", operationId = "get", tags = { "Zug" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Zugen by example", description = "Finds train configurations", operationId = "find", tags = { "Achsfolg" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ZugModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody ZugModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds an Zug", description = "Update a train", operationId = "update", tags = { "Zug" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody ZugModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Zug by name", description = "Update a train", operationId = "update", tags = { "Zug" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, @RequestBody ZugModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Deletes an Zug by name", description = "Delete a train", operationId = "update", tags = { "Zug" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PostMapping(ApiPaths.ZUG_CONSIST_ROOT + ApiPaths.SEPARATOR + ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr, @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        logPost(String.format(ApiPaths.ZUG_CONSIST_ROOT_LOG, ApiNames.ZUG, zugStr) + ": " + artikelId);

        return of(service.addConsist(zugStr, artikelId));
    }

    @PutMapping(ApiPaths.ZUG_CONSIST_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates a change to an Article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ZugModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position, @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        logPut(String.format(ApiPaths.ZUG_CONSIST_LOG, ApiNames.ZUG, zugStr, position) + ": " + artikelId);

        return of(service.updateConsist(zugStr, position, artikelId));
    }

    @DeleteMapping(ApiPaths.ZUG_CONSIST_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @Operation(summary = "Removes a change from an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr, @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position) {
        logDelete(String.format(ApiPaths.ZUG_CONSIST_LOG, ApiNames.ZUG, zugStr, position));

        return (service.deleteConsist(zugStr, position) ? noContent() : notFound()).build();
    }
}
