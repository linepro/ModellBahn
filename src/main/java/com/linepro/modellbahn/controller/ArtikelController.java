package com.linepro.modellbahn.controller;

import java.util.Map;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
import com.linepro.modellbahn.controller.base.AbstractItemController;
import com.linepro.modellbahn.controller.base.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.controller.base.FileServiceImpl;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.AnderungService;
import com.linepro.modellbahn.service.ArtikelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ARTIKEL)
@RestController 
@RequestMapping(ApiPaths.ARTIKEL)
public class ArtikelController extends AbstractItemController<ArtikelModel,Artikel> {

    private final AnderungService anderungService;
    
    private final FileServiceImpl fileService;

    @Autowired
    public ArtikelController(ArtikelService service, AnderungService anderungService, FileServiceImpl fileService) {
        super(service);

        this.anderungService = anderungService;
        this.fileService = fileService;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ArtikelModel create() {
        return new ArtikelModel();
    }
    
    @JsonCreator(mode= Mode.DELEGATING)
    public static AnderungModel createAnderung() {
        return new AnderungModel();
    }
    
    @GetMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Artikel by id", response = ArtikelModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        return super.get(getModel(artikelId));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Artikeln by example", response = ArtikelModel.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Artikel id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PRODUKT, value = "Artikel manufacturer", example = "[\"MARKLIN\",\"3000\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KAUFDATUM, value = "Artikel purchase date", example = "1967-02-15", dataType = "java.time.LocalDate", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.WAHRUNG, value = "Artikel currency", example = "HKD", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PREIS, value = "Artikel price", example = "110.50", dataType = "Number", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STUCK, value = "Artikel count", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STEUERUNG, value = "Artikel control method code", example = "FRU", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MOTOR_TYP, value = "Artikel motor type code", example = "SFCM", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LICHT, value = "Artikel light configuration code", example = "L1K", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KUPPLUNG, value = "Artikel coupling configuration code", example = "RELEX", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER, value = "Artikel decoderId", example = "00001", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.NAMEN, value = "Artikel code", example = "00001", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Artikel description", example = "Aus set", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANMERKUNG, value = "Artikel remarks", dataType = "String", example = "My favorite", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BELADUNG, value = "Artikel load", dataType = "String", example = "Holz", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STATUS, value = "Artikel status", dataType = "String", example = "GEKAUFT", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds an Artikel", response = ArtikelModel.class)
    public ResponseEntity<?> add(ArtikelModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Artikel by id", response = ArtikelModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, ArtikelModel model) {
        model.setArtikelId(artikelId);

        return super.update(model);
    }

    @DeleteMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Artikel by id")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        return super.delete(getModel(artikelId));
    }

    @PutMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture of a named Artikel", response = ArtikelModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.ARTIKEL, artikelId) + ": " + multipart.getOriginalFilename());

        ArtikelModel artikel = getModel(artikelId);

        artikel.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.ARTIKEL, ApiNames.ABBILDUNG, artikelId));

        return update(artikel);
    }

    @DeleteMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the picture of a named Artikel", response = ArtikelModel.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) throws Exception {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, ApiNames.ARTIKEL, artikelId));

        Optional<ArtikelModel> artikel = service.get(getModel(artikelId));
        
        if (artikel.isPresent()) {
            ArtikelModel artikelModel = artikel.get();

            if (artikelModel.getAbbildung() != null) {
                fileService.deleteFile(artikelModel.getAbbildung());
                artikelModel.setAbbildung(null);
                return update(artikelModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }

    @PostMapping(ApiPaths.ANDERUNG_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a new changed to an article", response = AnderungModel.class)
    public ResponseEntity<?> addAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, AnderungModel anderungModel) {
        logPost(String.format(ApiPaths.ANDERUNG_ROOT_LOG, ApiNames.ARTIKEL, artikelId) + ": " + anderungModel);

        anderungModel.setArtikel(getModel(artikelId));

        return created(anderungService.add(anderungModel));
    }

    @PutMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a change to an Article", response = AnderungModel.class)
    public ResponseEntity<?> updateAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId, AnderungModel anderungModel) {
        logPut(String.format(ApiPaths.ANDERUNG_LOG, ApiNames.ARTIKEL, artikelId, anderungId) + ": " + anderungModel);

        anderungModel.setArtikel(getModel(artikelId));
        anderungModel.setAnderungId(anderungId);

        return accepted(anderungService.update(anderungModel));
    }

    @DeleteMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Removes a change from an article")
    public ResponseEntity<?> deleteAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId) {
        logDelete(String.format(ApiPaths.ANDERUNG_LOG, ApiNames.ARTIKEL, artikelId, anderungId));

        return anderungService.delete(AnderungModel.builder().artikel(getModel(artikelId)).anderungId(anderungId).build()) ? noContent() : notFound();
    }

    protected ArtikelModel getModel(String artikelId) {
        return ArtikelModel.builder().artikelId(artikelId).build();
    }
}
