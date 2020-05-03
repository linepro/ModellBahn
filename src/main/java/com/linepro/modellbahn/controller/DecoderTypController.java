package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.AbstractItemController;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.controller.base.ApiPaths;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.DecoderTypAdressService;
import com.linepro.modellbahn.service.DecoderTypCvService;
import com.linepro.modellbahn.service.DecoderTypFunktionService;
import com.linepro.modellbahn.service.DecoderTypService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Api(value = ApiNames.DECODER_TYP)
@RestController
@RequestMapping(ApiPaths.DECODER_TYP)
public class DecoderTypController extends AbstractItemController<DecoderTypModel, DecoderTyp> {

    private final DecoderTypAdressService adressService;

    private final DecoderTypCvService cvService;

    private final DecoderTypFunktionService funktionService;

    @Autowired
    public DecoderTypController(DecoderTypService service, DecoderTypAdressService adressService, DecoderTypCvService cvService,
                    DecoderTypFunktionService funktionService) {
        super(service);

        this.adressService = adressService;
        this.cvService = cvService;
        this.funktionService = funktionService;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderTypModel create() {
        return new DecoderTypModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderTypAdressModel createAdress() {
        return new DecoderTypAdressModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderTypCvModel createCV() {
        return new DecoderTypCvModel();
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static DecoderTypFunktionModel createFunktion() {
        return new DecoderTypFunktionModel();
    }

    @GetMapping(ApiPaths.DECODER_TYP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = DecoderTypModel.class)
    public ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return super.get(getModel(herstellerStr, bestellNr));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.DropDown.class)
    @ApiOperation(value = "Finds DecoderTypen by example", response = DecoderTypModel.class, responseContainer = "List")
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

    @PostMapping(ApiPaths.ADD)
    @Consumes({
                    MediaType.APPLICATION_JSON
    })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a DecoderTyp", response = DecoderTypModel.class)
    public ResponseEntity<?> add(@RequestBody DecoderTypModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.DECODER_TYP_PATH)
    @Consumes({
                    MediaType.APPLICATION_JSON
    })
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 202, value = "Updates a DecoderTyp by hersteller and bestell nr", response = DecoderTypModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypModel model) {
        model.setHersteller(getHersteller(herstellerStr));
        model.setBestellNr(bestellNr);

        return super.update(model);
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTyp by hersteller and bestell nr", response = DecoderTypModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return super.delete(getModel(herstellerStr, bestellNr));
    }

    @GetMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = DecoderTypAdressModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
            logGet(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, index));

            return of(adressService.get(getAdressModel(herstellerStr, bestellNr, index)));
    }

    @PostMapping(ApiPaths.DECODER_TYP_ADRESS_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Adds a Adress to a DecoderTyp", response = DecoderTypAdressModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> addAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypAdressModel model) {
            logPost(String.format(ApiPaths.DECODER_TYP_ADRESS_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

            model.setDecoderTyp(getModel(herstellerStr, bestellNr));

            return created(adressService.add(model));
    }

    @PutMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Updates a DecoderTypAdress by hersteller and bestell nr", response = DecoderTypAdressModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index,
                    @RequestBody DecoderTypAdressModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, index) + ": " + model);

        model.setDecoderTyp(getModel(herstellerStr, bestellNr));
        model.setIndex(index);

        return accepted(adressService.update(model));
   }

    @DeleteMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @ApiOperation(value = "Finds a DecoderTypAdress by hersteller and bestell nr", response = DecoderTypAdressModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> deleteAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        logDelete(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, index));

        return adressService.delete(getAdressModel(herstellerStr, bestellNr, index)) ? noContent() : notFound();
    }

    @GetMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @ApiOperation(value = "Finds a DecoderTypCv by hersteller and bestell nr", response = DecoderTypCvModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv) {
            logGet(String.format(ApiPaths.DECODER_TYP_CV_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, cv));

            return of(cvService.get(getCvModel(herstellerStr, bestellNr, cv)));
    }

    @PostMapping(ApiPaths.DECODER_TYP_CV_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(code = 201, value = "Finds a DecoderTypCv by hersteller and bestell nr", response = DecoderTypCvModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> addCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypCvModel model) {
            logPost(String.format(ApiPaths.DECODER_TYP_CV_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

            model.setDecoderTyp(getModel(herstellerStr, bestellNr));

            return created(cvService.add(model));
     }

    @PutMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @ApiOperation(value = "Finds a DecoderTypCv by hersteller and bestell nr", response = DecoderTypCvModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv,
                    @RequestBody DecoderTypCvModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_CV_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, cv) + ": " + model);

        model.setDecoderTyp(getModel(herstellerStr, bestellNr));
        model.setCv(cv);

        return accepted(cvService.update(model));
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypCv by hersteller and bestell nr", response = DecoderTypCvModel.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 402, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> deleteCV(
            @PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv) {
        logDelete(String.format(ApiPaths.DECODER_TYP_CV_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, cv));

        return cvService.delete(getCvModel(herstellerStr, bestellNr, cv)) ? noContent() : notFound();
    }

    @GetMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = DecoderTypFunktionModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> getFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
                    @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        return of(funktionService.get(getFuntkionModel(herstellerStr, bestellNr, reihe, funktion)));
    }

    @PostMapping(ApiPaths.DECODER_TYP_FUNKTION_ROOT)
    @JsonView(Views.Public.class)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = DecoderTypFunktionModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> addFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypFunktionModel model) {
        logPost(String.format(ApiPaths.DECODER_TYP_FUNKTION_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

        model.setDecoderTyp(getModel(herstellerStr, bestellNr));

        return created(funktionService.add(model));
    }

    @PutMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @ApiOperation(value = "Finds a DecoderTypFunktion by hersteller and bestell nr", response = DecoderTypFunktionModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
                    @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion, @RequestBody DecoderTypFunktionModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, reihe, funktion) + ": " + model);

        model.setDecoderTyp(getModel(herstellerStr, bestellNr));
        model.setReihe(reihe);
        model.setFunktion(funktion);

        return accepted(funktionService.update(model));
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @ApiOperation(code = 204, value = "Deletes a DecoderTypFunktion by hersteller and bestell nr", response = DecoderTypFunktionModel.class)
    @ApiResponses({
                    @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 402, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> deleteFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
                    @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        logDelete(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, reihe, funktion));

        return funktionService.delete(getFuntkionModel(herstellerStr, bestellNr, reihe, funktion)) ? noContent() : notFound();
    }

    protected HerstellerModel getHersteller(String name) {
        return HerstellerModel.builder().name(name).build();
    }

    protected DecoderTypModel getModel(String herstellerStr, String bestellNr) {
        return DecoderTypModel.builder().hersteller(getHersteller(herstellerStr)).bestellNr(bestellNr).build();
    }

    protected DecoderTypAdressModel getAdressModel(String herstellerStr, String bestellNr, Integer index) {
        return DecoderTypAdressModel.builder().decoderTyp(getModel(herstellerStr, bestellNr)).index(index).build();
    }

    protected DecoderTypCvModel getCvModel(String herstellerStr, String bestellNr, Integer cv) {
        return DecoderTypCvModel.builder().decoderTyp(getModel(herstellerStr, bestellNr)).cv(cv).build();
    }

    protected DecoderTypFunktionModel getFuntkionModel(String herstellerStr, String bestellNr, Integer reihe, String funktion) {
        return DecoderTypFunktionModel.builder().decoderTyp(getModel(herstellerStr, bestellNr)).reihe(reihe).funktion(funktion).build();
    }
}
