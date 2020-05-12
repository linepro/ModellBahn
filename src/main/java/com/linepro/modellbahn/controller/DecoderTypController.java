package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.DecoderTypService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.DECODER_TYP)
@RestController
@RequestMapping(ApiPaths.DECODER_TYP)
@ExposesResourceFor(DecoderTypModel.class)
public class DecoderTypController extends AbstractItemController<DecoderTypModel> {

    private final DecoderTypService service;

    @Autowired
    public DecoderTypController(DecoderTypService service) {
        super(service);

        this.service = service;
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
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an DecoderTyp by name", description = "Finds a train", operationId = "get", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return of(service.get(herstellerStr, bestellNr));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds DecoderTypen by example", description = "Finds train configurations", operationId = "find", tags = { "Achsfolg" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DecoderTypModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody DecoderTypModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds an DecoderTyp", description = "Update a train", operationId = "update", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody DecoderTypModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an DecoderTyp by name", description = "Update a train", operationId = "update", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypModel model) {
        logPut(herstellerStr, bestellNr, model);

        return of(service.update(herstellerStr, bestellNr, model));
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Deletes an DecoderTyp by name", description = "Delete a train", operationId = "update", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        return (service.delete(herstellerStr, bestellNr) ? noContent() : notFound()).build();
    }

    @PostMapping(ApiPaths.DECODER_TYP_ADRESS_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypAdress" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypAdressModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
 
    public ResponseEntity<?> addAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypAdressModel model) {
            logPost(String.format(ApiPaths.DECODER_TYP_ADRESS_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

            return of(service.addAdress(herstellerStr, bestellNr, model));
    }

    @PutMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypAdress" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypAdressModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index,
                    @RequestBody DecoderTypAdressModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, index) + ": " + model);

        return of(service.updateAdress(herstellerStr, bestellNr, index, model));
   }

    @DeleteMapping(ApiPaths.DECODER_TYP_ADRESS_PATH)
    @Operation(summary = "Removes a change from an article", description = "", operationId = "", tags = { "DecoderTypAdress" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteAdress(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index) {
        logDelete(String.format(ApiPaths.DECODER_TYP_ADRESS_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, index));

        return (service.deleteAdress(herstellerStr, bestellNr, index) ? noContent() : notFound()).build();
    }

    @PostMapping(ApiPaths.DECODER_TYP_CV_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypCv" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypCvModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
     public ResponseEntity<?> addCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypCvModel model) {
            logPost(String.format(ApiPaths.DECODER_TYP_CV_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

            return of(service.addCv(herstellerStr, bestellNr, model));
     }

    @PutMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypCv" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypCvModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateCV(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv,
                    @RequestBody DecoderTypCvModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_CV_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, cv) + ": " + model);

        return of(service.updateCv(herstellerStr, bestellNr, cv, model));
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_CV_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Removes a change from an article", description = "", operationId = "", tags = { "DecoderTypCv" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteCV(
            @PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
            @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv) {
        logDelete(String.format(ApiPaths.DECODER_TYP_CV_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, cv));

        return (service.deleteCv(herstellerStr, bestellNr, cv) ? noContent() : notFound()).build();
    }

    @PostMapping(ApiPaths.DECODER_TYP_FUNKTION_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypFunktion" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypFunktionModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
     public ResponseEntity<?> addFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @RequestBody DecoderTypFunktionModel model) {
        logPost(String.format(ApiPaths.DECODER_TYP_FUNKTION_ROOT_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr) + ": " + model);

        return of(service.addFunktion(herstellerStr, bestellNr, model));
    }

    @PutMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "DecoderTypFunktion" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypFunktionModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
                    @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion, @RequestBody DecoderTypFunktionModel model) {
        logPut(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, reihe, funktion) + ": " + model);

        return of(service.updateFunktion(herstellerStr, bestellNr, reihe, funktion, model));
    }

    @DeleteMapping(ApiPaths.DECODER_TYP_FUNKTION_PATH)
    @Operation(summary = "Removes a change from an article", description = "", operationId = "", tags = { "DecoderTypFunktion" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteFunktion(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr,
                    @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe,
                    @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion) {
        logDelete(String.format(ApiPaths.DECODER_TYP_FUNKTION_LOG, ApiNames.DECODER_TYP, herstellerStr, bestellNr, reihe, funktion));

        return (service.deleteFunktion(herstellerStr, bestellNr, reihe, funktion) ? noContent() : notFound()).build();
    }
}
