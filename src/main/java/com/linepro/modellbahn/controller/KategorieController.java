package com.linepro.modellbahn.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
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
@RestController("KategorieController")
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
    @GetMapping(path = ApiPaths.GET_KATEGORIE, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Kategorie by name", description = "Finds a category", operationId = "get", tags = { ApiNames.KATEGORIE })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
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
    @GetMapping(path = ApiPaths.SEARCH_KATEGORIE, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Kategorieen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { ApiNames.KATEGORIE })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = KategorieModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<KategorieModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_KATEGORIE, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Kategorie", description = "Add a new UIC axle configuration", operationId = "add", tags = { ApiNames.KATEGORIE })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody KategorieModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_KATEGORIE, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Kategorie by name", description = "Update a category", operationId = "update", tags = { ApiNames.KATEGORIE })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = KategorieModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody KategorieModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_KATEGORIE)
    @Operation(summary = "Deletes an Kategorie by name", description = "Delete a category", operationId = "update", tags = { ApiNames.KATEGORIE })
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

    @GetMapping(path = ApiPaths.SEARCH_UNTER_KATEGORIE, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Kategorieen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { ApiNames.UNTER_KATEGORIE })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UnterKategorieModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> searchUnterKategorie(@RequestParam(ApiNames.KATEGORIEN) Optional<List<String>> kategorieen, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        Page<KategorieModel> page = service.searchUnterKategorie(kategorieen, pageNumber, pageSize);

        return found(page);
    }

    @PostMapping(path = ApiPaths.ADD_UNTER_KATEGORIE, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an UnterKategorie to a Kategorie", description = "", operationId = "add", tags = { ApiNames.UNTER_KATEGORIE })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UnterKategorieModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addUnterKategorie(@PathVariable(ApiNames.KATEGORIE) String kategorieStr, @RequestBody UnterKategorieModel unterKategorie) {
        return added(service.addUnterKategorie(kategorieStr, unterKategorie));
    }

    @PutMapping(path = ApiPaths.UPDATE_UNTER_KATEGORIE, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an UnterKategorie to a Kategorie", description = "", operationId = "update", tags = { ApiNames.UNTER_KATEGORIE })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UnterKategorieModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateUnterKategorie(@PathVariable(ApiNames.KATEGORIE) String kategorieStr,
            @PathVariable(ApiNames.UNTER_KATEGORIE) String unterKategorieStr, @RequestBody UnterKategorieModel unterKategorie) {
        return updated(service.updateUnterKategorie(kategorieStr, unterKategorieStr, unterKategorie));
    }

    @DeleteMapping(path = ApiPaths.DELETE_UNTER_KATEGORIE)
    @Operation(summary = "Removes an UnterKategorie from a Kategorie", description = "", operationId = "delete", tags = { ApiNames.UNTER_KATEGORIE })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteUnterKategorie(@PathVariable(ApiNames.KATEGORIE) String kategorieStr,
            @PathVariable(ApiNames.UNTER_KATEGORIE) String unterKategorieStr) {
        return deleted(service.deleteUnterKategorie(kategorieStr, unterKategorieStr));
    }
}
