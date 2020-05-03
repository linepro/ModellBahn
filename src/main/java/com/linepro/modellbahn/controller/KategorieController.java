package com.linepro.modellbahn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.linepro.modellbahn.controller.base.AbstractNamedItemController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.KategorieService;
import com.linepro.modellbahn.service.UnterKategorieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.KATEGORIE)
@RestController
@RequestMapping(ApiPaths.KATEGORIE)
public class KategorieController extends AbstractNamedItemController<KategorieModel,Kategorie> {

    private final UnterKategorieService unterKategorieService;
    
    @Autowired
    public KategorieController(KategorieService service,UnterKategorieService unterKategorieService) {
        super(service);
        
        this.unterKategorieService = unterKategorieService;
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
    @ApiOperation(value = "Finds an Kategorie by name", response = KategorieModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Kategorieen by example", response = KategorieModel.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.ID, value = "Kategorie id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.NAMEN, value = "Kategorie code", example = "LOKOMOTIV", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Kategorie description", example = "Lokomotiv", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),})
    public ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds an Kategorie", response = KategorieModel.class)
    public ResponseEntity<?> add(KategorieModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Kategorie by name", response = KategorieModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, KategorieModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Kategorie by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @GetMapping(ApiPaths.UNTER_KATEGORIE_ROOT)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds UnterKategorieen by kategorie", response = UnterKategorieModel.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.KATEGORIEN, value = "List of Kategorie names", dataType = "[Ljava.lang.String;", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),})
    @ApiResponses({@ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> searchUnterKategorie(@RequestParam(ApiNames.KATEGORIEN) List<String> kategorieen, @RequestBody Map<String, String> arguments) {
        logGet(String.format(ApiPaths.UNTER_KATEGORIEN_LOG, ApiNames.KATEGORIE) + ": " + kategorieen);

        return unterKategorieService.search(kategorieen, getPageRequest(arguments));
    }

    @PostMapping(ApiPaths.UNTER_KATEGORIE_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds an UnterKategorie to a kategorie", response = UnterKategorieModel.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> addUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, UnterKategorieModel unterKategorie) {
        logPost(String.format(ApiPaths.UNTER_KATEGORIE_ROOT_LOG, ApiNames.KATEGORIE, kategorieStr) + ": " + unterKategorie);

        unterKategorie.setKategorie(getModel(kategorieStr));

        return created(unterKategorieService.add(unterKategorie));
    }

    @PutMapping(ApiPaths.UNTER_KATEGORIE_PATH)
    @ApiOperation(code = 202, value = "Updates an UnterKategorie by kategorie and name", response = UnterKategorieModel.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> updateUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, UnterKategorieModel unterKategorie) {
        logPut(String.format(ApiPaths.UNTER_KATEGORIE_LOG, ApiNames.KATEGORIE, kategorieStr, unterKategorieStr) + ": " + unterKategorie);

        unterKategorie.setKategorie(getModel(kategorieStr));

        return accepted(unterKategorieService.update(unterKategorie));
    }

    @DeleteMapping(ApiPaths.UNTER_KATEGORIE_PATH)
    @ApiOperation(code = 204, value = "Deletes an UnterKategorie by kategorie and name")
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> deleteUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        logDelete(String.format(ApiPaths.UNTER_KATEGORIE_LOG, ApiNames.KATEGORIE, kategorieStr, unterKategorieStr));

        return unterKategorieService.delete(UnterKategorieModel.builder().kategorie(getModel(kategorieStr)).name(unterKategorieStr).build()) ? noContent() : notFound();
    }
}
