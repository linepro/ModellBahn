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
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.request.BahnverwaltungRequest;
import com.linepro.modellbahn.service.criterion.BahnverwaltungCriterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;
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
public class BahnverwaltungController extends NamedItemController<BahnverwaltungModel, BahnverwaltungRequest> {

    private final BahnverwaltungService service;

    @Autowired
    public BahnverwaltungController(BahnverwaltungService service) {
        super(service);

        this.service = service;
    }

    @Override
    @GetMapping(path = ApiPaths.GET_BAHNVERWALTUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
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

    @GetMapping(path = ApiPaths.SEARCH_BAHNVERWALTUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds Bahnverwaltungen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedBahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(BahnverwaltungCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_BAHNVERWALTUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds an Bahnverwaltung", description = "Update a railway company", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody BahnverwaltungRequest request) {
        return super.add(request);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_BAHNVERWALTUNG, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Bahnverwaltung by name", description = "Update a railway company", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody BahnverwaltungRequest request) {
        return super.update(name, request);
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

    @PutMapping(path = ApiPaths.ADD_BAHNVERWALTUNG_ABBILDUNG, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Add an Bahnverwaltung picture", description = "Adds or updates the picture of a named Bahnverwaltung", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Bahnverwaltung not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String name, @RequestParam(ApiNames.ABBILDUNG) MultipartFile multipart) {
        return updated(service.updateAbbildung(name, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_BAHNVERWALTUNG_ABBILDUNG, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Delete an Bahnverwaltung picture", description = "Deletes the picture of a named Bahnverwaltung", operationId = "update", tags = { ApiNames.BAHNVERWALTUNG }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BahnverwaltungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Bahnverwaltung not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String name) {
        return updated(service.deleteAbbildung(name));
    }
}
