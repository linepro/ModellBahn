package com.linepro.modellbahn.rest.service;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.model.impl.Wahrung;
import com.linepro.modellbahn.persistence.repository.IWahrungRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractNamedItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * WahrungService. CRUD service for Wahrung
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.WAHRUNG)
@RestController
@RequestMapping(ApiPaths.WAHRUNG)
public class WahrungService extends AbstractNamedItemService<IWahrung,Wahrung> {

    @Autowired
    public WahrungService(IWahrungRepository persister) {
        super(persister);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static IWahrung create() {
        return new Wahrung();
    }
    
    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Wahrung by name", response = IWahrung.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Wahrungen by example", response = IWahrung.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Wahrung id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Wahrung code", example = "GBP", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Wahrung description", example = "Pound Serling", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DEZIMAL, value = "Wahrung dezimal", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public    ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Wahrung", response = IWahrung.class)
    public  ResponseEntity<?> add(IWahrung entity) {
        return super.add(entity);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Wahrung by name", response = IWahrung.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, IWahrung entity) {
        return super.update(name, entity);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Wahrung by name")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
}
