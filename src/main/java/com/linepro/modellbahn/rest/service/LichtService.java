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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.persistence.repository.ILichtRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractNamedItemService;
import com.linepro.modellbahn.rest.util.AcceptableMediaTypes;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.FileUploadHandler;
import com.linepro.modellbahn.rest.util.IFileUploadHandler;
import com.linepro.modellbahn.util.StaticContentFinder;

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
public class LichtService extends AbstractNamedItemService<ILicht,Licht> {

    @Autowired
    public LichtService(ILichtRepository persister) {
        super(persister);
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ILicht create() {
        return new Licht();
    }
    
    @Override
    @GetMapping(ApiPaths.NAME_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Licht by name", response = ILicht.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Lichten by example", response = ILicht.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiNames.ID, value = "Licht id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.NAMEN, value = "Licht code", example = "L1V", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Licht description", example = "Einfach-Spitzensignal vorne", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
})
    public    ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Licht", response = ILicht.class)
    public  ResponseEntity<?> add(ILicht entity) {
        return super.add(entity);
    }

    @Override
    @PutMapping(ApiPaths.NAME_PART)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Licht by name", response = ILicht.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name, ILicht entity) {
        return super.update(name, entity);
    }

    @Override
    @DeleteMapping(ApiPaths.NAME_PART)
    @ResponseBody
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Licht by name")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        return super.delete(name);
    }

    @PutMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture for a named Licht", response = ILicht.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name,
            @PathVariable("file") MultipartFile multipart) {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), name) + ": " + multipart.getOriginalFilename());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(multipart, AcceptableMediaTypes.IMAGE_TYPES)) {
                return badRequest(getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            ILicht licht = persister.findByName(name);

            if (licht != null) {
                java.nio.file.Path file = handler.upload(ApiNames.LICHT, new String[] {name}, multipart);

                licht.setAbbildung(file);

                persister.saveAndFlush((Licht) licht);

                return ok(licht);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @DeleteMapping(ApiPaths.ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the picture for a named Licht", response = ILicht.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.NAME_PARAM_NAME) String name) {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), name));
        
        try {
            ILicht licht = persister.findByName(name);

            if (licht != null && licht.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(licht.getAbbildung());

                licht.setAbbildung(null);

                persister.saveAndFlush((Licht) licht);

                return ok(licht);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }
}
