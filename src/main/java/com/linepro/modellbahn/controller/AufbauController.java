package com.linepro.modellbahn.controller;

import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractNamedItemController;
import com.linepro.modellbahn.controller.base.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.controller.base.FileServiceImpl;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.AufbauService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AufbauService. CRUD service for Aufbau
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.AUFBAU)
@RestController
@RequestMapping(ApiPaths.AUFBAU)
@ExposesResourceFor(AufbauModel.class)
public class AufbauController extends AbstractNamedItemController<AufbauModel,Aufbau> {

    private final FileServiceImpl fileService;
    
    @Autowired
    public AufbauController(AufbauService service, FileServiceImpl fileService) {
        super(service);
        
        this.fileService = fileService;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static AufbauModel create() {
        return new AufbauModel();
    }
    
    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Aufbau by name", response = AufbauModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Aufbauen by example", response = AufbauModel.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Aufbau id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Aufbau code", example = "LK", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Aufbau description", example = "Fahrgestell der Lok aus Metall", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds an Aufbau", response = AufbauModel.class)
    public ResponseEntity<?> add(AufbauModel dto) {
        return super.add(dto);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Aufbau by name", response = AufbauModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, AufbauModel dto) {
        return super.update(name, dto);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Aufbau by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PutMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture of a named Aufbau", response = AufbauModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.AUFBAU, name) + ": " + multipart.getOriginalFilename());

        AufbauModel aufbau = getModel(name);

        aufbau.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.AUFBAU, ApiNames.ABBILDUNG, name));

        return update(aufbau);
    }

    @DeleteMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Deletes the picture of a named Aufbau", response = AufbauModel.class)
    @ApiResponses({
        @ApiResponse(code = 304, message = "Not Modified"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.AUFBAU, name));

        Optional<AufbauModel> aufbau = service.get(getModel(name));
        
        if (aufbau.isPresent()) {
            AufbauModel aufbauModel = aufbau.get();

            if (aufbauModel.getAbbildung() != null) {
                fileService.deleteFile(aufbauModel.getAbbildung());
                aufbauModel.setAbbildung(null);
                return update(aufbauModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }
}
