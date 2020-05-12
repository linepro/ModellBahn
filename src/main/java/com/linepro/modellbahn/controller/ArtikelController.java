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
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.ArtikelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.ARTIKEL)
@RestController 
@RequestMapping(ApiPaths.ARTIKEL)
@ExposesResourceFor(ArtikelModel.class)
public class ArtikelController extends AbstractItemController<ArtikelModel> {

    protected final ArtikelService service;

    @Autowired
    public ArtikelController(ArtikelService service) {
        super(service);
        
        this.service = service;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ArtikelModel create() {
        return new ArtikelModel();
    }
    
    @JsonCreator(mode= Mode.DELEGATING)
    public static AnderungModel createAnderung() {
        return new AnderungModel();
    }
    
    @GetMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an Artikel by name", description = "Finds an article", operationId = "get", tags = { "Artikel" })
        @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArtikelModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        logGet(artikelId);

        return of(service.get(artikelId));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Artikelen by example", description = "Finds articles", operationId = "find", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArtikelModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody ArtikelModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add a new Artikel", description = "Add a new article", operationId = "add", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArtikelModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody ArtikelModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Artikel by name", description = "Update an article", operationId = "update", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArtikelModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, ArtikelModel model) {
        return of(service.update(artikelId, model));
    }

    @DeleteMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Deletes an Artikel by name", description = "Delete an article", operationId = "update", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        logDelete(artikelId);

        return (service.delete(artikelId) ? noContent() : notFound()).build();
    }

    @PutMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add an Artikel picture", description = "Adds or updates the picture of a named Artikel", operationId = "update", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArtikelModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.ARTIKEL, artikelId) + ": " + multipart.getOriginalFilename());

        return of(service.updateAbbildung(artikelId, multipart));
    }

    @DeleteMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Delete an Artikel picture", description = "Deletes the picture of a named Artikel", operationId = "update", tags = { "Artikel" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.ARTIKEL, artikelId));
        
        return of(service.deleteAbbildung(artikelId));
    }

    @PostMapping(ApiPaths.ANDERUNG_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AnderungModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @RequestBody AnderungModel anderungModel) {
        logPost(String.format(ApiPaths.ANDERUNG_ROOT_LOG, ApiNames.ARTIKEL, artikelId) + ": " + anderungModel);

        return of(service.addAnderung(artikelId, anderungModel));
    }

    @PutMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates a change to an Article", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AnderungModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId, @RequestBody AnderungModel anderungModel) {
        logPut(String.format(ApiPaths.ANDERUNG_LOG, ApiNames.ARTIKEL, artikelId, anderungId) + ": " + anderungModel);

        return of(service.updateAnderung(artikelId, anderungId, anderungModel));
    }

    @DeleteMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Removes a change from an article", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId) {
        logDelete(String.format(ApiPaths.ANDERUNG_LOG, ApiNames.ARTIKEL, artikelId, anderungId));

        return (service.deleteAnderung(artikelId, anderungId) ? noContent() : notFound()).build();
    }
}
