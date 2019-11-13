package com.linepro.modellbahn.rest.service;

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
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.persistence.repository.IKategorieRepository;
import com.linepro.modellbahn.persistence.repository.IUnterKategorieRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractNamedItemService;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

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
public class KategorieService extends AbstractNamedItemService<IKategorie, Kategorie> {

    private final IUnterKategorieRepository unterKategoriePersister;

    @Autowired
    public KategorieService(IKategorieRepository persister, IUnterKategorieRepository unterKategoriePersister) {
        super(persister);

        this.unterKategoriePersister = unterKategoriePersister;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IKategorie create() {
        return new Kategorie();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IUnterKategorie createUnterKategorie() {
        return new UnterKategorie();
    }

    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Kategorie by name", response = IKategorie.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Kategorieen by example", response = IKategorie.class, responseContainer = "List")
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
    @ApiOperation(code = 201, value = "Adds an Kategorie", response = IKategorie.class)
    public ResponseEntity<?> add(IKategorie entity) {
        return super.add(entity);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Kategorie by name", response = IKategorie.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, IKategorie entity) {
        return super.update(name, entity);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Kategorie by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @GetMapping(ApiNames.UNTER_KATEGORIEN)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds UnterKategorieen by kategorie", response = IUnterKategorie.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.KATEGORIEN, value = "List of Kategorie names", dataType = "[Ljava.lang.String;", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),})
    @ApiResponses({@ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> searchUnterKategorie(@RequestBody Map<String, String> arguments, @RequestParam(ApiNames.KATEGORIEN) final List<String> kategorien) {
        try {
            logGet(String.format(ApiPaths.UNTER_KATEGORIEN_LOG, getEntityClassName()) + ": " + kategorien);

            return super.subSearch(unterKategoriePersister, getExample(arguments, UnterKategorie.class), getPageRequest(arguments));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PostMapping(ApiPaths.UNTER_KATEGORIE_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Adds an UnterKategorie to a kategorie", response = IUnterKategorie.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> addUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr, IUnterKategorie newUnterKategorie) {
        logPost(String.format(ApiPaths.UNTER_KATEGORIE_ROOT_LOG, getEntityClassName(), kategorieStr) + ": "
                + newUnterKategorie);

        try {

            IKategorie kategorie = persister.findByName(kategorieStr);

            if (kategorie == null) {
                return badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr));
            }

            newUnterKategorie.setDeleted(false);

            kategorie.addUnterKategorie(newUnterKategorie);

            persister.saveAndFlush((Kategorie) kategorie);

            return created(newUnterKategorie);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.UNTER_KATEGORIE_PATH)
    @ApiOperation(code = 202, value = "Updates an UnterKategorie by kategorie and name", response = IUnterKategorie.class)
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> updateUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr, IUnterKategorie newUnterKategorie) {
        logPut(String.format(ApiPaths.UNTER_KATEGORIE_LOG, getEntityClassName(), kategorieStr, unterKategorieStr) + ": "
                + newUnterKategorie);

        try {
            IKategorie kategorie = persister.findByName(kategorieStr);

            if (kategorie == null) {
                return badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr));
            }

            UnterKategorie unterKategorie = unterKategoriePersister.findByKategorieAndName(kategorieStr, unterKategorieStr);

            if (unterKategorie == null) {
                return badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr));
            } else if (newUnterKategorie.getKategorie() == null) {
                newUnterKategorie.setKategorie(kategorie);
            } else if (!newUnterKategorie.getKategorie().equals(kategorie)) {
                // Attempt to change kategorie not allowed
                return badRequest(ApiMessages.UNTERKATEGORIE_KATEGORIE_FIXED);
            }

            unterKategorie.setBezeichnung(newUnterKategorie.getBezeichnung());

            unterKategorie = unterKategoriePersister.saveAndFlush(unterKategorie);

            return accepted(unterKategorie);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.UNTER_KATEGORIE_PATH)
    @ApiOperation(code = 204, value = "Deletes an UnterKategorie by kategorie and name")
    @ApiResponses({@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> deleteUnterKategorie(@PathVariable(ApiPaths.KATEGORIE_PARAM_NAME) String kategorieStr,
            @PathVariable(ApiPaths.UNTER_KATEGORIE_PARAM_NAME) String unterKategorieStr) {
        logDelete(String.format(ApiPaths.UNTER_KATEGORIE_LOG, getEntityClassName(), kategorieStr, unterKategorieStr));

        try {
            IKategorie kategorie = persister.findByName(kategorieStr);

            if (kategorie == null) {
                return badRequest(getMessage(ApiMessages.KATEGORIE_DOES_NOT_EXIST, kategorieStr));
            }

            IUnterKategorie unterKategorie = unterKategoriePersister.findByKategorieAndName(kategorieStr,
                    unterKategorieStr);

            if (unterKategorie == null) {
                return badRequest(
                        getMessage(ApiMessages.UNTER_KATEGORIE_DOES_NOT_EXIST, kategorieStr, unterKategorieStr));
            }

            kategorie.removeUnterKategorie(unterKategorie);

            persister.saveAndFlush((Kategorie) kategorie);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }
}
