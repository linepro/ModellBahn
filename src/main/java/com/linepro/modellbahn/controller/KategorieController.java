package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.KategorieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.KATEGORIE)
@RestController
@RequestMapping(ApiPaths.KATEGORIE)
@ExposesResourceFor(KategorieModel.class)
public class KategorieController extends NamedItemController<KategorieModel> {

    private final KategorieService service;
    
    @Autowired
    public KategorieController(KategorieService service) {
        super(service);
        
        this.service = service;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static KategorieModel create() {
        return new KategorieModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static UnterKategorieModel createUnterKategorie() {
        return new UnterKategorieModel();
    }

    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an Kategorie by name", description = "Finds a category", operationId = "get", tags = { "Kategorie" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
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
    @Operation(summary = "Finds Kategorieen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { "Kategorie" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = KategorieModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody KategorieModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add a new Kategorie", description = "Add a new UIC axle configuration", operationId = "add", tags = { "Kategorie" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody KategorieModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Kategorie by name", description = "Update a category", operationId = "update", tags = { "Kategorie" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, @RequestBody KategorieModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Deletes an Kategorie by name", description = "Delete a category", operationId = "update", tags = { "Kategorie" })
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

    @GetMapping(ApiPaths.UNTER_KATEGORIE_ROOT)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Kategorieen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { "UnterKategorie" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnterKategorieModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> searchUnterKategorie(@RequestParam(ApiNames.KATEGORIEN) List<String> kategorieen, @RequestBody UnterKategorieModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        Page<UnterKategorieModel> page = service.searchUnterKategorie(kategorieen, model, pageNumber, pageSize);

        return page.hasContent() ? ok(page) : notFound().build();
    }

    @PostMapping(ApiPaths.UNTER_KATEGORIE_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UnterKategorieModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorieModel unterKategorie) {
        return added(service.addUnterKategorie(kategorieStr, unterKategorie));
    }

    @PutMapping(ApiPaths.UNTER_KATEGORIE_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates a change to an Article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UnterKategorieModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, UnterKategorieModel unterKategorie) {
        return updated(service.updateUnterKategorie(kategorieStr, unterKategorieStr, unterKategorie));
    }

    @DeleteMapping(ApiPaths.UNTER_KATEGORIE_PATH)
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
    public ResponseEntity<?> deleteUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        return deleted(service.deleteUnterKategorie(kategorieStr, unterKategorieStr));
    }
}
