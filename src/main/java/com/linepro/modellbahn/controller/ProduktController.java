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
import com.linepro.modellbahn.controller.base.FileService;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.ProduktService;

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
public class ProduktController extends AbstractItemController<ProduktModel,Produkt> {

    private final ProduktService service;
    
    private final FileService fileService;
    
    @Autowired
    public ProduktController(ProduktService service, FileService fileService) {
        super(service);

        this.service = service;
        this.fileService = fileService;
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ProduktModel create() {
        return new ProduktModel();
    }

    @JsonCreator(mode= Mode.DELEGATING)
    public static ProduktTeilModel createProduktTeil() {
        return new ProduktTeilModel();
    }
    
    @GetMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return super.get(getModel(herstellerStr, bestellNr));
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
    public ResponseEntity<?> search(@RequestBody Map<String,String> arguments) {
        return super.search(arguments);
    }

    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Produkt", response = Produkt.class)
    public ResponseEntity<?> add(ProduktModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.PRODUKT_PART)
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, ProduktModel model) {
        model.setHersteller(getHersteller(herstellerStr));
        model.setBestellNr(bestellNr);

        return super.update(model);
    }

    @DeleteMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Produkt by hersteller and bestell nr")
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return super.delete(getModel(herstellerStr, bestellNr));
    }

    @PostMapping(ApiPaths.PRODUKT_TEIL_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a sub produkt  a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> addTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, ProduktTeilModel teil) {
        logPost(String.format(ApiPaths.PRODUKT_TEIL_ROOT_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + teil);

        teil.setProdukt(getModel(herstellerStr, bestellNr));

        return created(service.addTeil(teil));
    }

    @PutMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a sub produkt a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> updateTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr, Integer anzahl) {
        logPut(String.format(ApiPaths.PRODUKT_TEIL_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr) + ":" + anzahl);

        ProduktTeilModel teil = getTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr);

        teil.setAnzahl(anzahl);
        
        return accepted(service.updateTeil(teil));
    }

    @DeleteMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a sub produkt for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> deleteTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_TEIL_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));

        return service.deleteTeil(getTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr)) ? noContent() : notFound();
    }

    @PutMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the picture of a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        ProduktModel produkt = getModel(herstellerStr, bestellNr);

        produkt.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ABBILDUNG, herstellerStr, bestellNr));

        return update(produkt);
    }

    @DeleteMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the picture of a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));
        
        Optional<ProduktModel> produkt = service.get(getModel(herstellerStr, bestellNr));
        
        if (produkt.isPresent()) {
            ProduktModel produktModel = produkt.get();

            if (produktModel.getAbbildung() != null) {
                fileService.deleteFile(produktModel.getAbbildung());
                produktModel.setAbbildung(null);
                return update(produktModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }

    @PutMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> updateAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        ProduktModel produkt = getModel(herstellerStr, bestellNr);

        produkt.setAnleitungen(fileService.updateFile(AcceptableMediaTypes.DOCUMENT_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ANLEITUNGEN, herstellerStr, bestellNr));

        return update(produkt);
    }

    @DeleteMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
    @ApiOperation(code = 204, value = "Deletes the instructions for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> deleteAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));
        
        Optional<ProduktModel> produkt = service.get(getModel(herstellerStr, bestellNr));
        
        if (produkt.isPresent()) {
            ProduktModel produktModel = produkt.get();

            if (produktModel.getAnleitungen() != null) {
                fileService.deleteFile(produktModel.getAnleitungen());
                produktModel.setAnleitungen(null);
                return update(produktModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }

    @PutMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds or updates the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> updateExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        ProduktModel produkt = getModel(herstellerStr, bestellNr);

        produkt.setExplosionszeichnung(fileService.updateFile(AcceptableMediaTypes.DOCUMENT_TYPES, multipart, ApiNames.PRODUKT, ApiNames.EXPLOSIONSZEICHNUNG, herstellerStr, bestellNr));

        return update(produkt);
    }

    @DeleteMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes the drawing for a Produkt by hersteller and bestell nr", response = Produkt.class)
    public ResponseEntity<?> deleteExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));

        Optional<ProduktModel> produkt = service.get(getModel(herstellerStr, bestellNr));
        
        if (produkt.isPresent()) {
            ProduktModel produktModel = produkt.get();

            if (produktModel.getExplosionszeichnung() != null) {
                fileService.deleteFile(produktModel.getExplosionszeichnung());
                produktModel.setExplosionszeichnung(null);
                return update(produktModel);
            }
            
            return notModified();
        }
        
        return notFound();
    }

    protected HerstellerModel getHersteller(String name) {
        return HerstellerModel.builder().name(name).build();
    }

    protected ProduktModel getModel(String herstellerStr, String bestellNr) {
        return ProduktModel.builder().hersteller(getHersteller(herstellerStr)).bestellNr(bestellNr).build();
    }

    protected ProduktTeilModel getTeil(String herstellerStr, String bestellNr, String teilHerstellerStr,
            String teilBestellNr) {
        return ProduktTeilModel.builder()
                .produkt(getModel(herstellerStr, bestellNr))
                .teil(getModel(teilHerstellerStr, teilBestellNr))
                .build();
    }
}
