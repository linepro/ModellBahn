package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.model.ZugTypModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.VorbildService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * VorbildService. CRUD service for Vorbild
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.VORBILD)
@RestController
@RequestMapping(ApiPaths.VORBILD)
@ExposesResourceFor(VorbildModel.class)
public class VorbildController extends AbstractItemController<VorbildModel> {

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

    @GetMapping(ApiPaths.VORBILD_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an Vorbild by name", description = "Finds a prototype", operationId = "get", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) {
        logGet(gattung);

        return of(service.get(gattung));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Vorbilder by example", description = "Finds prototypes", operationId = "find", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ZugTypModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody VorbildModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add a new Vorbild", description = "Add a new UIC axle configuration", operationId = "add", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(VorbildModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.VORBILD_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Vorbild by name", description = "Update a prototyp", operationId = "update", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung, VorbildModel model) {
        return of(service.update(gattung, model));
    }

    @DeleteMapping(ApiPaths.VORBILD_PART)
    @JsonView(Views.Public.class) 
    @Operation(summary = "Deletes an Vorbild by name", description = "Delete a prototyp", operationId = "update", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
   public ResponseEntity<?> delete(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) {
        logDelete(gattung);

        return (service.delete(gattung) ? noContent() : notFound()).build();
    }

     @PutMapping(ApiPaths.VORBILD_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Vorbild by name", description = "Update a prototype", operationId = "update", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.VORBILD, gattung) + ": " + multipart.getOriginalFilename());

        return of(service.updateAbbildung(gattung, multipart));
    }

    @DeleteMapping(ApiPaths.VORBILD_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Delete an Vorbild picture", description = "Deletes the picture of a named Vorbild", operationId = "update", tags = { "Vorbild" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VorbildModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Vorbild not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.GATTUNG_PARAM_NAME) String gattung) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.VORBILD, gattung));

        return of(service.deleteAbbildung(gattung));
    }
}
