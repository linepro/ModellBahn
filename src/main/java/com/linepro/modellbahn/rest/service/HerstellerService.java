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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.impl.Hersteller;
import com.linepro.modellbahn.persistence.repository.IHerstellerRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractNamedItemService;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * HerstellerService. CRUD service for Hersteller
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.HERSTELLER)
@RestController
@RequestMapping(ApiPaths.HERSTELLER)
public class HerstellerService extends AbstractNamedItemService<IHersteller,Hersteller> {

    @Autowired
    public HerstellerService(IHerstellerRepository persister) {
        super(persister);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static IHersteller create() {
        return new Hersteller();
    }
    
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Hersteller by name", response = IHersteller.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Herstelleren by example", response = IHersteller.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Hersteller id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Hersteller code", example = "MARKLIN", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Hersteller description", example = "Märklin", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.TELEFON, value = "Hersteller telefon", example = "+49 (0) 71 61 608-0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.URL, value = "Hersteller url", example = "https://www.maerklin.de/", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public    ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Hersteller", response = IHersteller.class)
    public  ResponseEntity<?> add(Hersteller entity) {
        return super.add(entity);
    }

    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Hersteller by name", response = IHersteller.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, Hersteller entity) {
        return super.update(name, entity);
    }

    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Hersteller by name")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }
}
