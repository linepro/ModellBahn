package com.linepro.modellbahn.controller;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.service.impl.BahnverwaltungService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * BahnverwaltungService. CRUD service for Bahnverwaltung
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.BAHNVERWALTUNG)
@RestController("Bahnverwaltung")
@ExposesResourceFor(BahnverwaltungModel.class)
@SecurityRequirement(name = "BasicAuth")
public class BahnverwaltungController extends NamedItemController<BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungController(BahnverwaltungService service) {
        super(service);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static BahnverwaltungModel create() {
        return new BahnverwaltungModel();
    }

    @Override
    @GetMapping(path = ApiPaths.GET_BAHNVERWALTUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Bahnverwaltung by name", description = "Finds an UIC axle configuration", operationId = "get",  tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return super.get(name);
    }

    @Schema(name = ApiNames.BAHNVERWALTUNG + "Page")
    class PagedBahnverwaltungModel extends PagedSchema<BahnverwaltungModel>{}

    @Override
    @GetMapping(path = ApiPaths.SEARCH_BAHNVERWALTUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Bahnverwaltungen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedBahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(@RequestBody Optional<BahnverwaltungModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_BAHNVERWALTUNG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Bahnverwaltung", description = "Update a railway company", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody BahnverwaltungModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_BAHNVERWALTUNG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Bahnverwaltung by name", description = "Update a railway company", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody BahnverwaltungModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_BAHNVERWALTUNG)
    @Operation(summary = "Deletes an Bahnverwaltung by name", description = "Delete a railway company", operationId = "delete", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
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
