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
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.IProduktTeil;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.persistence.repository.IProduktRepository;
import com.linepro.modellbahn.persistence.repository.IProduktTeilRepository;
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

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.PRODUKT)
@RestController
@RequestMapping(ApiPaths.PRODUKT)
public class ProduktService extends AbstractItemService<IProdukt,Produkt> {

    private final IProduktRepository persister;
    
    private final IProduktTeilRepository teilPersister;
    
    @Autowired
    public ProduktService(IProduktRepository persister, IProduktTeilRepository teilPersister) {
        super(persister);

        this.persister = persister;
        this.teilPersister = teilPersister;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static IProdukt create() {
        return new Produkt();
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static IProduktTeil createProduktTeil() {
        return new ProduktTeil();
    }
    
    @GetMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.get(persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Produkten by example", response = Produkt.class, responseContainer = "List")
    @ApiImplicitParams({
        @ApiImplicitParam( name = ApiNames.ID, value = "Produkt id", dataType = "Long", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.HERSTELLER, value = "Produkt manufacturer", example = "MARKLIN", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BESTELL_NR, value = "Produkt part number", example = "3000", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BEZEICHNUNG, value = "Produkt description", example = "Dampftenderlok BR 89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.UNTER_KATEGORIE, value = "Category and subcategory", example = "[\"LOKOMOTIV\",\"DAMPF\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MASSSTAB, value = "Produkt scale", example = "H0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SPURWEITE, value = "Produkt track gauge", example = "H0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.EPOCH, value = "Produkt era", example = "III", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAHNVERWALTUNG, value = "Produkt railway company", example = "DB", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.GATTUNG, value = "Produkt rolling stock class", example = "BR89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BETREIBSNUMMER, value = "Produkt service number", example = "89 006", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.BAUZEIT, value = "Produkt construction date", example = "1934-01-01", dataType = "java.time.LocalDate", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.VORBILD, value = "Produkt prototype", example = "BR89.0", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ACHSFOLG, value = "Produkt axle configuration", example = "C1HT", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.ANMERKUNG, value = "Produkt remarks", example = "Aus set 29400", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.SONDERMODELL, value = "Produkt SonderModell", example = "MHI", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.AUFBAU, value = "Produkt construction", example = "LK", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LICHT, value = "Produkt light configuration", example = "L1V", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.KUPPLUNG, value = "Produkt coupling configuration", example = "RELEX", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.STEUERUNG, value = "Produkt control method", example = "FRU", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DECODER_TYP, value = "Produkt decoder type", example = "[\"ESU\",\"62400\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.MOTOR_TYP, value = "Produkt motor type", example = "SFCM", dataType = "String", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.LANGE, value = "Produkt length", example = "11.0", dataType = "Number", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam( name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public    ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Produkt", response = Produkt.class)
    public  ResponseEntity<?> add(IProdukt entity) {
        try {
            return super.add(entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.PRODUKT_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IProdukt entity) {
        try {
            return super.update(persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr), entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Produkt by hersteller and bestell nr")
    public  ResponseEntity<?> delete(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        try {
            return super.delete(persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PostMapping(ApiPaths.PRODUKT_TEIL_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a sub produkt  a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> addTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, IProduktTeil teil) {
        logPost(String.format(ApiPaths.PRODUKT_TEIL_ROOT_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + teil);

        try {
            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt == null) {
                return badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            if (cyclic(produkt, teil.getTeil())) {
                return badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }
            
            teil.setProdukt(produkt);
        
            produkt.addTeil(teil);

            persister.saveAndFlush((Produkt) produkt);

            return created(teil);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    private boolean cyclic(IProdukt produkt, IProdukt teil) {
        return produkt.equals(teil);
    }

    @PutMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a sub produkt a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> updateTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr, Integer anzahl) {
        logPut(String.format(ApiPaths.PRODUKT_TEIL_LOG, getEntityClassName(), herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr) + ":" + anzahl);

        try {
            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt == null) {
                return badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            ProduktTeil produktTeil = teilPersister.findByProduktAndTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr);

            if (produktTeil == null) {
                return badRequest(getMessage(ApiMessages.PRODUKT_TEIL_DOES_NOT_EXIST, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));
            }

			produktTeil.setAnzahl(anzahl);

            produktTeil = teilPersister.saveAndFlush(produktTeil);

            return created(produktTeil);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @DeleteMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a sub produkt for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> deleteTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_TEIL_LOG, getEntityClassName(), herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));

        try {
            Produkt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt == null) {
                return badRequest(getMessage(ApiMessages.PRODUKT_DOES_NOT_EXIST, herstellerStr, bestellNr));
            }

            ProduktTeil produktTeil = teilPersister.findByProduktAndTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr);

            if (produktTeil == null) {
                return badRequest(getMessage(ApiMessages.PRODUKT_TEIL_DOES_NOT_EXIST, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));
            }

            produkt.removeTeil(produktTeil);

            persister.saveAndFlush(produkt);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @PutMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) {
        logPut(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(multipart, AcceptableMediaTypes.IMAGE_TYPES)) {
                return badRequest(getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, multipart);

                produkt.setAbbildung(file);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @DeleteMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the picture for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, getEntityClassName(), herstellerStr, bestellNr));
        
        try {
            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAbbildung());

                produkt.setAbbildung(null);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @PutMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> updateAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) {
        logPut(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(multipart, AcceptableMediaTypes.DOCUMENT_TYPES)) {
                return badRequest(getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, multipart);

                produkt.setAnleitungen(file);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @DeleteMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
    @ApiOperation(code = 204, value = "Deletes the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> deleteAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, getEntityClassName(), herstellerStr, bestellNr));
        
        try {
            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getAnleitungen());

                produkt.setAnleitungen(null);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @PutMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> updateExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) {
        logPut(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, getEntityClassName(), herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        IFileUploadHandler handler = new FileUploadHandler();

        try {
            if (!handler.isAcceptable(multipart, AcceptableMediaTypes.DOCUMENT_TYPES)) {
                return badRequest(getMessage(ApiMessages.INVALID_FILE, multipart.getOriginalFilename()));
            }

            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null) {
                java.nio.file.Path file = handler.upload(ApiNames.PRODUKT, new String[] { herstellerStr, bestellNr }, multipart);

                produkt.setExplosionszeichnung(file);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }

    @DeleteMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public  ResponseEntity<?> deleteExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, getEntityClassName(), herstellerStr, bestellNr));

        try {
            IProdukt produkt = persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);

            if (produkt != null && produkt.getAbbildung() != null) {
                StaticContentFinder.getStore().removeFile(produkt.getExplosionszeichnung());

                produkt.setExplosionszeichnung(null);

                persister.saveAndFlush((Produkt) produkt);

                return ok(produkt);
            }
        } catch (Exception e) {
            return getResponse(e);
        }

        return notFound();
    }
}
