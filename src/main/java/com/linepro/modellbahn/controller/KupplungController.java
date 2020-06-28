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
import com.linepro.modellbahn.model.KupplungModel;
import com.linepro.modellbahn.service.impl.KupplungService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * KupplungService. CRUD service for Kupplung
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.KUPPLUNG)
@RestController("KupplungController")
@ExposesResourceFor(KupplungModel.class)
public class KupplungController extends NamedItemController<KupplungModel> {

    private final KupplungService service;
    
    @Autowired
    public KupplungController(KupplungService service) {
        super(service);
        
        this.service = service;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static KupplungModel create() {
        return new KupplungModel();
    }
    
    @Override
    @GetMapping(path = ApiPaths.GET_KUPPLUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Kupplung by name", description = "Finds a coupling configuration", operationId = "get", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KupplungModel.class)) }),
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
    @GetMapping(path = ApiPaths.SEARCH_KUPPLUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Kupplungen by example", description = "Finds coupling configurations", operationId = "get", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = KupplungModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
})
    public ResponseEntity<?> search(@RequestBody Optional<KupplungModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_KUPPLUNG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Kupplung", description = "Update a coupling configuration", operationId = "update", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KupplungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Kupplung not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody KupplungModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_KUPPLUNG, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Kupplung by name", description = "Update a coupling configuration", operationId = "update", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KupplungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Kupplung not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody KupplungModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_KUPPLUNG)
    @Operation(summary = "Deletes an Kupplung by name", description = "Delete a coupling configuration", operationId = "update", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Kupplung not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name) {
        return super.delete(name);
    }

    @PutMapping(path = ApiPaths.ADD_KUPPLUNG_ABBILDUNG, consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Add an Kupplung picture", description = "Adds or updates the picture of a named Kupplung", operationId = "update", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KupplungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Kupplung not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String name, @RequestParam("abbildung") MultipartFile multipart) throws Exception {
        return of(service.updateAbbildung(name, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_KUPPLUNG_ABBILDUNG, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete an Kupplung picture", description = "Deletes the picture of a named Kupplung", operationId = "update", tags = { "Kupplung" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KupplungModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Kupplung not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String name) throws Exception {
        return of(service.deleteAbbildung(name));
    }
}
