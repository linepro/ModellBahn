package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.ProduktModel;
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

    @GetMapping(ApiPaths.GET_DECODER_TYP)
    @Operation(summary = "Finds an DecoderTyp by name", description = "Finds a train", operationId = "get", tags = { "DecoderTyp" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return of(service.get(herstellerStr, bestellNr));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH_DECODER_TYP)
    @Operation(summary = "Finds DecoderTypen by example", description = "Finds train configurations", operationId = "find", tags = { "Achsfolg" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DecoderTypModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<DecoderTypModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @PostMapping(ApiPaths.ADD_DECODER_TYP)
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

    @PutMapping(ApiPaths.UPDATE_DECODER_TYP)
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
    public ResponseEntity<?> update(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypModel model) {
        return updated(service.update(herstellerStr, bestellNr, model));
    }

    @DeleteMapping(ApiPaths.DELETE_DECODER_TYP)
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
    public ResponseEntity<?> delete(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return deleted(service.delete(herstellerStr, bestellNr));
    }

    @PostMapping(ApiPaths.ADD_DECODER_TYP_ADRESS)
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
 
    public ResponseEntity<?> addAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypAdressModel model) {
            return added(service.addAdress(herstellerStr, bestellNr, model));
    }

    @PutMapping(ApiPaths.UPDATE_DECODER_TYP_ADRESS)
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
    public ResponseEntity<?> updateAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.INDEX) Integer index,
                    @RequestBody DecoderTypAdressModel model) {
        return updated(service.updateAdress(herstellerStr, bestellNr, index, model));
   }

    @DeleteMapping(ApiPaths.DELETE_DECODER_TYP_ADRESS)
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
    public ResponseEntity<?> deleteAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.INDEX) Integer index) {
        return deleted(service.deleteAdress(herstellerStr, bestellNr, index));
    }

    @PostMapping(ApiPaths.ADD_DECODER_TYP_CV)
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
     public ResponseEntity<?> addCV(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypCvModel model) {
            return added(service.addCv(herstellerStr, bestellNr, model));
     }

    @PutMapping(ApiPaths.UPDATE_DECODER_TYP_CV)
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
    public ResponseEntity<?> updateCV(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.CV) Integer cv,
                    @RequestBody DecoderTypCvModel model) {
        return updated(service.updateCv(herstellerStr, bestellNr, cv, model));
    }

    @DeleteMapping(ApiPaths.DELETE_DECODER_TYP_CV)
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
            @PathVariable(ApiNames.HERSTELLER) String herstellerStr,
            @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @PathVariable(ApiNames.CV) Integer cv) {
        return deleted(service.deleteCv(herstellerStr, bestellNr, cv));
    }

    @PostMapping(ApiPaths.ADD_DECODER_TYP_FUNKTION)
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
     public ResponseEntity<?> addFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypFunktionModel model) {
        return added(service.addFunktion(herstellerStr, bestellNr, model));
    }

    @PutMapping(ApiPaths.UPDATE_DECODER_TYP_FUNKTION)
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
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.REIHE) Integer reihe,
                    @PathVariable(ApiNames.FUNKTION) String funktion, @RequestBody DecoderTypFunktionModel model) {
        return updated(service.updateFunktion(herstellerStr, bestellNr, reihe, funktion, model));
    }

    @DeleteMapping(ApiPaths.DELETE_DECODER_TYP_FUNKTION)
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
    public ResponseEntity<?> deleteFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.REIHE) Integer reihe,
                    @PathVariable(ApiNames.FUNKTION) String funktion) {
        return deleted(service.deleteFunktion(herstellerStr, bestellNr, reihe, funktion));
    }
    
    @PostMapping(ApiPaths.ADD_DECODER_TYP_ANLEITUNGEN)
    @Operation(summary = "Adds Decoder instructions", description = "Adds or updates the instructions for a named Decoder", operationId = "update", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateAnleitungen(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @RequestParam("anleitungen") MultipartFile multipart) throws Exception {
        return updated(service.updateAnleitungen(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(ApiPaths.DELETE_DECODER_TYP_ANLEITUNGEN)
    @Operation(summary = "Removes instructions from an Product", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteAnleitungen(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) throws Exception {
        return updated(service.deleteAnleitungen(herstellerStr, bestellNr));
    }

}
