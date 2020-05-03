package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractItemController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.DecoderAdressService;
import com.linepro.modellbahn.service.DecoderCvService;
import com.linepro.modellbahn.service.DecoderFunktionService;
import com.linepro.modellbahn.service.DecoderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.DECODER)
@RestController
@RequestMapping(ApiPaths.DECODER)
public class DecoderController extends AbstractItemController<DecoderModel, Decoder> {

    private final DecoderService service;

    private final DecoderAdressService adressService;

    private final DecoderCvService cvService;

    private final DecoderFunktionService funktionService;

    @Autowired
    public DecoderController(DecoderService service, DecoderAdressService adressService, DecoderCvService cvService,
                    DecoderFunktionService funktionService) {
        super(service);

        this.service = service;
        this.adressService = adressService;
        this.cvService = cvService;
        this.funktionService = funktionService;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderModel create() {
        return new DecoderModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderAdressModel createAdress() {
        return new DecoderAdressModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderCvModel createCV() {
        return new DecoderCvModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderFunktionModel createFunktion() {
        return new DecoderFunktionModel();
    }

    @PostMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Decoder by hersteller and bestell nr", response = DecoderModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> addDecoder(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return ok(service.addDecoder(herstellerStr, bestellNr));
    }

    @GetMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a Decoder by decoderId", response = DecoderModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return super.get(getModel(decoderId));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds Decoderen by example", response = DecoderModel.class, responseContainer = "List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = ApiNames.ID, value = "Decoder id", dataType = "Long", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.DECODER_ID, value = "Decoder code", example = "00001", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.DECODER_TYP, value = "Decoder type", example = "[\"ESU\",\"62400\"]", dataType = "[Ljava.lang.String;", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.BEZEICHNUNG, value = "Decoder description", example = "LokSound M4", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.PROTOKOLL, value = "Decoder protocoll code", example = "MFX", dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.FAHRSTUFE, value = "Decoder speed steps", example = "27", dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.DELETED, value = "If true search for soft deleted items", example = "false", dataType = "Boolean", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.PAGE_NUMBER, value = "0 based page number for paged queries", example = "1", dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = ApiNames.PAGE_SIZE, value = "Page size for paged queries", example = "10", dataType = "Integer", paramType = "query"),
    })
    public ResponseEntity<?> search(@RequestBody Map<String, String> arguments) {
        return super.search(arguments);
    }

    @PutMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a Decoder by decoderId", response = DecoderModel.class)
    public ResponseEntity<?> update(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId, DecoderModel model) {
        model.setDecoderId(decoderId);

        return super.update(model);
    }

    @DeleteMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 204, value = "Deletes a Decoder by decoderId", response = DecoderModel.class)
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return super.delete(getModel(decoderId));
    }

    @GetMapping(ApiPaths.DECODER_ADRESS_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderAdress by decoderId and position", response = DecoderAdress.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getAdress(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        logGet(String.format(ApiPaths.DECODER_ADRESS_LOG, ApiNames.DECODER, decoderId, index));

        return of(adressService.get(getAdressModel(decoderId, index)));
    }

    @PutMapping(ApiPaths.DECODER_ADRESS_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderAdress by decoderId and position", response = DecoderAdress.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index, @RequestParam(ApiNames.ADRESS) Integer adress) {
        logPut(String.format(ApiPaths.DECODER_ADRESS_LOG, ApiNames.DECODER, decoderId, index) + ": " + adress);

        DecoderAdressModel model = getAdressModel(decoderId, index);

        model.setAdress(adress);

        return of(adressService.update(model));
    }

    @GetMapping(ApiPaths.DECODER_CV_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderCv by decoderId and cv", response = DecoderCv.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getCv(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId, @RequestParam(ApiPaths.CV_PARAM_NAME) Integer cv) {
        logGet(String.format(ApiPaths.DECODER_CV_LOG, ApiNames.DECODER, decoderId, cv));

        return of(cvService.get(getCvModel(decoderId, cv)));
    }

    @PutMapping(ApiPaths.DECODER_CV_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderCv by decoderId and cv", response = DecoderCv.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateCv(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv, @RequestParam(ApiNames.WERT) Integer wert) {
        logPut(String.format(ApiPaths.DECODER_CV_LOG, ApiNames.DECODER, decoderId, cv) + ": " + wert);

        DecoderCvModel model = getCvModel(decoderId, cv);

        model.setWert(wert);

        return of(cvService.update(model));
    }

    @GetMapping(ApiPaths.DECODER_FUNKTION_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderFunktion by decoderId and fn", response = DecoderFunktion.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getFunktion(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        logGet(String.format(ApiPaths.DECODER_FUNKTION_LOG, ApiNames.DECODER, decoderId, reihe, funktion));

        return of(funktionService.get(getFunktionModel(decoderId, reihe, funktion)));
    }

    @PutMapping(ApiPaths.DECODER_FUNKTION_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderFunktion by decoderId and fn", response = DecoderFunktion.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion,
                    @RequestParam(ApiNames.BEZEICHNUNG) String bezeichnung) {
        logPut(String.format(ApiPaths.DECODER_FUNKTION_LOG, ApiNames.DECODER, decoderId, reihe, funktion) + ": " + bezeichnung);

        DecoderFunktionModel model = getFunktionModel(decoderId, reihe, funktion);

        model.setBezeichnung(bezeichnung);

        return of(funktionService.update(model));
    }

    protected DecoderModel getModel(String decoderId) {
        return DecoderModel.builder().decoderId(decoderId).build();
    }

    protected HerstellerModel getHersteller(String name) {
        return HerstellerModel.builder().name(name).build();
    }

    protected DecoderTypModel getModel(String herstellerStr, String bestellNr) {
        return DecoderTypModel.builder().hersteller(getHersteller(herstellerStr)).bestellNr(bestellNr).build();
    }

    protected DecoderAdressModel getAdressModel(String decoderId, Integer index) {
        return DecoderAdressModel.builder().decoder(getModel(decoderId)).index(index).build();
    }

    protected DecoderCvModel getCvModel(String decoderId, Integer cv) {
        return DecoderCvModel.builder().decoder(getModel(decoderId)).cv(cv).build();
    }

    protected DecoderFunktionModel getFunktionModel(String decoderId, Integer reihe, String funktion) {
        return DecoderFunktionModel.builder().decoder(getModel(decoderId)).reihe(reihe).funktion(funktion).build();
    }
}
