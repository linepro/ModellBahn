package com.linepro.modellbahn.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractNamedItemController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.ZugConsistService;
import com.linepro.modellbahn.service.ZugService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ZUG)
@RestController
@RequestMapping(ApiPaths.ZUG)
public class ZugController extends AbstractNamedItemController<ZugModel, Zug> {

    private final ZugConsistService consistService;

    public ZugController(ZugService service, ZugConsistService consistService) {
        super(service);

        this.consistService = consistService;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static ZugModel create() {
        return new ZugModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static ZugConsistModel createZugConsist() {
        return new ZugConsistModel();
    }

    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a IZug by name", response = ZugModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Zugen by example", response = ZugModel.class, responseContainer = "List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = ApiNames.ID, value = "Zug id", dataType = "Long", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.ZUG_TYP, value = "Zug typ", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.NAMEN, value = "Zug code", example = "BAVARIA", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Zug description", example = "TEE „Bavaria“", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Zug", response = ZugModel.class)
    public ResponseEntity<?> add(ZugModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Zug by name", response = ZugModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, ZugModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Zug by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PostMapping(ApiPaths.ZUG_CONSIST_ROOT + ApiPaths.SEPARATOR + ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a vehicle to the end of a named Zug", response = ZugConsistModel.class)
    public ResponseEntity<?> addConsist(@ApiParam(value = "Train code", example = "BAVARIA") @PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr,
                    @ApiParam(value = "Artikel id", example = "00001", required = true) @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        logPost(String.format(ApiPaths.ZUG_CONSIST_ROOT_LOG, ApiNames.ZUG, zugStr) + ": " + artikelId);

        return created(consistService.add(ZugConsistModel.builder().zug(getModel(zugStr)).artikel(getArtikel(artikelId)).build()));
    }

    @PutMapping(ApiPaths.ZUG_CONSIST_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a vehicle in a named Zug", response = ZugConsistModel.class)
    public ResponseEntity<?> updateConsist(@ApiParam(value = "Train code", example = "BAVARIA") @PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr,
                    @ApiParam(value = "Contiguous 1 based position in the train (1 = head)", example = "1") @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position,
                    @ApiParam(value = "Artikel id", example = "00001", required = true) @RequestParam(ApiNames.ARTIKEL_ID) String artikelId) {
        logPut(String.format(ApiPaths.ZUG_CONSIST_LOG, ApiNames.ZUG, zugStr, position) + ": " + artikelId);

        return accepted(consistService
                        .update(ZugConsistModel.builder().zug(getModel(zugStr)).position(position).artikel(getArtikel(artikelId)).build()));
    }

    @DeleteMapping(ApiPaths.ZUG_CONSIST_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Removes a vehicle from a named Zug all other vehicles will be renumbered", response = ZugModel.class)
    public ResponseEntity<?> deleteConsist(@ApiParam(value = "Train code", example = "BAVARIA") @PathVariable(ApiPaths.ZUG_PARAM_NAME) String zugStr,
                    @ApiParam(value = "Contiguous 1 based position in the train (1 = head)", example = "1") @PathVariable(ApiPaths.POSITION_PARAM_NAME) Integer position) {
        logDelete(String.format(ApiPaths.ZUG_CONSIST_LOG, ApiNames.ZUG, zugStr, position));

        return consistService.delete(ZugConsistModel.builder().zug(getModel(zugStr)).position(position).build()) ? accepted(get(getModel(zugStr))) :
                        notModified();
    }

    protected ArtikelModel getArtikel(String artikelId) {
        return ArtikelModel.builder().artikelId(artikelId).build();
    }
}
