package com.linepro.modellbahn.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractNamedItemController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.entity.SonderModell;
import com.linepro.modellbahn.model.SonderModellModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.SonderModellService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * SonderModellService. CRUD service for SonderModell
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.SONDERMODELL)
@RestController
@RequestMapping(ApiPaths.SONDERMODELL)
public class SonderModellController extends AbstractNamedItemController<SonderModellModel,SonderModell> {

    @Autowired
    public SonderModellController(SonderModellService service) {
        super(service);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static SonderModellModel create() {
        return new SonderModellModel();
    }
    
    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a SonderModell by name", response = SonderModellModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds SonderModellen by example", response = SonderModellModel.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "SonderModell id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "SonderModell code", example = "MHI", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "SonderModell description", example = "Märklin Handler Initiative", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a SonderModell", response = SonderModellModel.class)
    public ResponseEntity<?> add(SonderModellModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a SonderModell by name", response = SonderModellModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, SonderModellModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a SonderModell by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
}
