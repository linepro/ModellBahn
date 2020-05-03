package com.linepro.modellbahn.controller;

import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractNamedItemController;
import com.linepro.modellbahn.controller.base.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.controller.base.FileServiceImpl;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.LichtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * LichtService. CRUD service for Licht
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.LICHT)
@RestController
@RequestMapping(ApiPaths.LICHT)
public class LichtController extends AbstractNamedItemController<LichtModel,Licht> {

    private final FileServiceImpl fileService;
    
    @Autowired
    public LichtController(LichtService service, FileServiceImpl fileService) {
        super(service);
        
        this.fileService = fileService;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static LichtModel create() {
        return new LichtModel();
    }
    
    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Licht by name", response = LichtModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Lichten by example", response = LichtModel.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.ID, value = "Licht id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.NAMEN, value = "Licht code", example = "L1V", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Licht description", example = "Einfach-Spitzensignal vorne", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
})
    public ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Licht", response = LichtModel.class)
    public ResponseEntity<?> add(LichtModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Licht by name", response = LichtModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, LichtModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)

    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Licht by name")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PutMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture of a named Licht", response = LichtModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.LICHT, name) + ": " + multipart.getOriginalFilename());

        LichtModel licht = getModel(name);

        licht.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.LICHT, ApiNames.ABBILDUNG, name));

        return update(licht);
    }

    @DeleteMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Deletes the picture of a named Licht", response = LichtModel.class)
    @ApiResponses({
        @ApiResponse(code = 304, message = "Not Modified"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.LICHT, name));

        Optional<LichtModel> licht = service.get(getModel(name));
        
        if (licht.isPresent()) {
            LichtModel lichtModel = licht.get();

            if (lichtModel.getAbbildung() != null) {
                fileService.deleteFile(lichtModel.getAbbildung());
                lichtModel.setAbbildung(null);
                return update(lichtModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }
}
