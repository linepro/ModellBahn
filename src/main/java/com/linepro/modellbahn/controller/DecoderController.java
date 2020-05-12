package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;

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
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.DecoderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.DECODER)
@RestController
@RequestMapping(ApiPaths.DECODER)
@ExposesResourceFor(DecoderModel.class)
public class DecoderController extends AbstractItemController<DecoderModel> {

    private final DecoderService service;

    @Autowired
    public DecoderController(DecoderService service) {
        super(service);

        this.service = service;
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

    @GetMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Finds an DecoderTyp by name", description = "Finds a train", operationId = "get", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return of(service.get(decoderId));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Achsfolgen by example", description = "Finds UIC axle configurations", operationId = "find", tags = { "Achsfolg" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DecoderModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody DecoderModel model, Integer pageNumber, Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @PostMapping(ApiPaths.DECODER_TYP_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds an DecoderTyp", description = "Update a train", operationId = "update", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> addDecoder(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return of(service.add(herstellerStr, bestellNr));
    }

    @PutMapping(ApiPaths.DECODER_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an DecoderTyp by name", description = "Update a train", operationId = "update", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId, DecoderModel model) {
        return of(service.update(decoderId, model));
    }

    @DeleteMapping(ApiPaths.DECODER_PART)
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
    public ResponseEntity<?> delete(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId) {
        return (service.delete(decoderId) ? noContent() : notFound()).build();
    }

    @PutMapping(ApiPaths.DECODER_ADRESS_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderAdressModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.INDEX_PARAM_NAME) Integer index, @RequestParam(ApiNames.ADRESS) Integer adress) {
        logPut(String.format(ApiPaths.DECODER_ADRESS_LOG, ApiNames.DECODER, decoderId, index) + ": " + adress);

        return of(service.updateAdress(decoderId, index, adress));
    }

    @PutMapping(ApiPaths.DECODER_CV_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderCvModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateCv(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.CV_PARAM_NAME) Integer cv, @RequestParam(ApiNames.WERT) Integer wert) {
        logPut(String.format(ApiPaths.DECODER_CV_LOG, ApiNames.DECODER, decoderId, cv) + ": " + wert);

        return of(service.updateCv(decoderId, cv, wert));
    }

    @PutMapping(ApiPaths.DECODER_FUNKTION_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a new change to an article", description = "", operationId = "", tags = { "UnterKategorie" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderFunktionModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
   public ResponseEntity<?> updateFunktion(@PathVariable(ApiPaths.DECODER_ID_PARAM_NAME) String decoderId,
                    @PathVariable(ApiPaths.REIHE_PARAM_NAME) Integer reihe, @PathVariable(ApiPaths.FUNKTION_PARAM_NAME) String funktion,
                    @RequestParam(ApiNames.BEZEICHNUNG) String bezeichnung) {
        logPut(String.format(ApiPaths.DECODER_FUNKTION_LOG, ApiNames.DECODER, decoderId, reihe, funktion) + ": " + bezeichnung);

        return of(service.updateFunktion(decoderId, reihe, funktion, bezeichnung));
    }
}
