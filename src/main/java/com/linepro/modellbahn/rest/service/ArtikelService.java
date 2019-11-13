package com.linepro.modellbahn.rest.service;

import java.util.Map;

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
import com.linepro.modellbahn.model.IAnderung;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.impl.Anderung;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.persistence.repository.IAnderungRepository;
import com.linepro.modellbahn.persistence.repository.IArtikelRepository;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.AbstractItemService;
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
 * ArtikelService. CRUD service for Artikel
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.ARTIKEL)
@RestController 
@RequestMapping(ApiPaths.ARTIKEL)
public class ArtikelService extends AbstractItemService<IArtikel,Artikel> {

    private final IArtikelRepository persister;
    
    private final IAnderungRepository anderungPersister;

    @Autowired
    public ArtikelService(IArtikelRepository persister, IAnderungRepository anderungPersister) {
        super(persister);

        this.persister = persister;
        this.anderungPersister = anderungPersister;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static IArtikel create() {
        return new Artikel();
    }
    
    @JsonCreator(mode= Mode.DELEGATING)
    public static IAnderung createAnderung() {
        return new Anderung();
    }
    
    @GetMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds an Artikel by id", response = IArtikel.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String id) {
        return super.get(persister.findByArtikelId(id));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Artikeln by example", response = IArtikel.class, responseContainer = "List")
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
    public    ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds an Artikel", response = IArtikel.class)
    public  ResponseEntity<?> add(IArtikel entity) {
        return super.add(entity);
    }

    @PutMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates an Artikel by id", response = IArtikel.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, IArtikel entity) {
        return super.update(persister.findByArtikelId(artikelId), entity);
    }

    @DeleteMapping(ApiPaths.ARTIKEL_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes an Artikel by id")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        return super.delete(persister.findByArtikelId(artikelId));
    }

    @PutMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Adds or updates the image for a named Artikel", response = IArtikel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId,
            @PathVariable("file") MultipartFile multipart) {
        logPut(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), artikelId) + ": " + multipart.getOriginalFilename());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(multipart, AcceptableMediaTypes.IMAGE_TYPES)) {
                return badRequest(getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            Artikel artikel = persister.findByArtikelId(artikelId);

            if (artikel != null) {
                java.nio.file.Path file = handler.upload(ApiNames.ARTIKEL, new String[] { artikelId }, multipart);

                artikel.setAbbildung(file);

                persister.saveAndFlush(artikel);

                return ok(artikel);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @DeleteMapping(ApiPaths.ARTIKEL_ABBILDUNG_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Deletes the image for a named Artikel", response = IArtikel.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId) {
        logDelete(String.format(ApiPaths.ABBILDUNG_LOG, getEntityClassName(), artikelId));
        
        try {
            IArtikel artikel = persister.findByArtikelId(artikelId);

            if (artikel != null && artikel.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(artikel.getAbbildung());

                artikel.setAbbildung(null);

                persister.saveAndFlush((Artikel) artikel);

                return ok(artikel);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @PostMapping(ApiPaths.ANDERUNG_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a new changed to an article", response = IAnderung.class)
    public ResponseEntity<?> addAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, IAnderung newAnderung) {
        logPost(String.format(ApiPaths.ANDERUNG_ROOT_LOG, getEntityClassName(), artikelId) + ": " + newAnderung);

        try {
            Artikel artikel = persister.findByArtikelId(artikelId);

            if (artikel == null) {
                return badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId));
            }

            artikel.addAnderung(newAnderung);

            persister.saveAndFlush(artikel);

            return created(newAnderung);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a change to an Article", response = IAnderung.class)
    public ResponseEntity<?> updateAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId, IAnderung newAnderung) {
        logPut(String.format(ApiPaths.ANDERUNG_LOG, getEntityClassName(), artikelId, anderungId) + ": " + newAnderung);

        try {
            IArtikel artikel = persister.findByArtikelId(artikelId);

            if (artikel == null) {
                return badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId));
            }

            Anderung anderung = anderungPersister.findByArtikelIdAndAnderungId(artikelId, anderungId);

            if (anderung == null) {
                return badRequest(getMessage(ApiMessages.ANDERUNG_DOES_NOT_EXIST, artikelId, anderungId));
            }

            newAnderung.setArtikel(artikel);
            newAnderung.setAnderungId(anderungId);

            anderung = anderungPersister.saveAndFlush(anderung);

            return created(anderung);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.ANDERUNG_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Removes a change from an article")
    public  ResponseEntity<?> deleteAnderung(@PathVariable(ApiPaths.ARTIKEL_ID_PARAM_NAME) String artikelId, @PathVariable(ApiPaths.ANDERUNG_ID_PARAM_NAME) Integer anderungId) {
        logDelete(String.format(ApiPaths.ANDERUNG_LOG, getEntityClassName(), artikelId, anderungId));

        try {
            Artikel artikel = persister.findByArtikelId(artikelId);

            if (artikel == null) {
                return badRequest(getMessage(ApiMessages.ARTIKEL_DOES_NOT_EXIST, artikelId));
            }

            IAnderung anderung = anderungPersister.findByArtikelIdAndAnderungId(artikelId, anderungId);

            if (anderung == null) {
                return badRequest(getMessage(ApiMessages.ANDERUNG_DOES_NOT_EXIST, artikelId, anderungId));
            }

            artikel.removeAnderung(anderung);

            // TODO: resequence anderungId for remaining items?
            
            persister.saveAndFlush(artikel);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }
}
