package com.linepro.modellbahn.rest.service;

import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.model.IZugConsist;
import com.linepro.modellbahn.model.impl.Zug;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.persistence.repository.IZugConsistRepository;
import com.linepro.modellbahn.persistence.repository.IZugRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractNamedItemService;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ZUG)
@RestController
@RequestMapping(ApiPaths.ZUG)
public class ZugService extends AbstractNamedItemService<IZug, Zug> {

    private final IZugRepository persister;
    
    private final IZugConsistRepository consistPersister;

    public ZugService(IZugRepository persister, IZugConsistRepository consistPersister) {
        super(persister);

        this.persister = persister;
        this.consistPersister = consistPersister;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IZug create() {
        return new Zug();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static IZugConsist createZugConsist() {
        return new ZugConsist();
    }

    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a IZug by name", response = IZug.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Zugen by example", response = IZug.class, responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = ApiNames.ID, value = "Zug id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.ZUG_TYP, value = "Zug typ", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.NAMEN, value = "Zug code", example = "BAVARIA", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Zug description", example = "TEE „Bavaria“", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),})
    public    ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Zug", response = IZug.class)
    public  ResponseEntity<?> add(IZug entity) {
        return super.add(entity);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Zug by name", response = IZug.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, IZug entity) {
        return super.update(name, entity);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Zug by name")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PostMapping(ApiPaths.ZUG_CONSIST_ROOT+ ApiPaths.SEPARATOR + ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a vehicle to the end of a named Zug", response = IZugConsist.class)
    public  ResponseEntity<?> addConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr, IArtikel artikel) {
        logPost(String.format(ApiPaths.ZUG_CONSIST_ROOT_LOG, getEntityClassName(), zugStr) + ": " + artikel);

        try {
            IZug zug = persister.findByName(zugStr);

            if (zug == null) {
                return badRequest(getMessage(ApiMessages.ZUG_DOES_NOT_EXIST, zugStr));
            }

            IZugConsist zugConsist = new ZugConsist(null, zug, zug.getConsist().size() + 1, artikel, false);

            zug.addConsist(zugConsist);

            persister.saveAndFlush((Zug) zug);

            return created(zugConsist);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.ZUG_CONSIST_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a vehicle in a named Zug", response = IZugConsist.class)
    public  ResponseEntity<?> updateConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr,
            @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position, IArtikel artikel) {
        logPut(String.format(ApiPaths.ZUG_CONSIST_LOG, getEntityClassName(), zugStr, position) + ": " + artikel);

        try {
            ZugConsist consist = consistPersister.findByZugAndPosition(zugStr, position);

            if (consist == null) {
                return badRequest(getMessage(ApiMessages.ZUG_CONSIST_DOES_NOT_EXIST, zugStr, position));
            }

            consist.setArtikel(artikel);

            consist = consistPersister.saveAndFlush(consist);

            return created(consist);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.ZUG_CONSIST_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Removes a vehicle from a named Zug")
    public  ResponseEntity<?> deleteConsist(@PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr,
            @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position) {
        logDelete(String.format(ApiPaths.ZUG_CONSIST_LOG, getEntityClassName(), zugStr, position));

        try {
            IZugConsist zugConsist = consistPersister.findByZugAndPosition(zugStr, position);

            if (zugConsist == null) {
                return badRequest(getMessage(ApiMessages.ZUG_CONSIST_DOES_NOT_EXIST, zugStr, position));
            }

            IZug zug = zugConsist.getZug();

            zug.removeConsist(zugConsist);

            // TODO: resequence position for remaining items

            persister.saveAndFlush((Zug) zug);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }
}
