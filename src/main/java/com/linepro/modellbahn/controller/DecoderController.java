package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
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
@RestController("DecoderController")
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

    @GetMapping(path = ApiPaths.GET_DECODER, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Decoder by id", description = "Finds a decoder", operationId = "get", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.DECODER_ID) String decoderId) {
        return of(service.get(decoderId));
    }

    @Override
    @GetMapping(path = ApiPaths.SEARCH_DECODER, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Decoders by example", description = "Finds decoders", operationId = "find", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DecoderModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<DecoderModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @PostMapping(path = ApiPaths.ADD_DECODER, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Decoder by Typ", description = "Add a decoder", operationId = "add", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> addDecoder(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderModel model) {
        return added(service.add(herstellerStr, bestellNr, model));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Decoder by id", description = "Update a decoder", operationId = "update", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.DECODER_ID) String decoderId, @RequestBody DecoderModel model) {
        return updated(service.update(decoderId, model));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER)
    @Operation(summary = "Deletes an Decoder by id", description = "Delete a decoder", operationId = "delete", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.DECODER_ID) String decoderId) {
        return deleted(service.delete(decoderId));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_ADRESS, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a decoder address", description = "", operationId = "update", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderAdressModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiNames.DECODER_ID) String decoderId, @PathVariable(ApiNames.INDEX) Integer index, @RequestParam(ApiNames.ADRESS) Integer adress) {
        return updated(service.updateAdress(decoderId, index, adress));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_CV, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a decoder cv", description = "", operationId = "update", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderCvModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateCv(@PathVariable(ApiNames.DECODER_ID) String decoderId, @PathVariable(ApiNames.CV) Integer cv, @RequestParam(ApiNames.WERT) Integer wert) {
        return updated(service.updateCv(decoderId, cv, wert));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_FUNKTION, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a decoder function", description = "", operationId = "update", tags = { ApiNames.DECODER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderFunktionModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
   public ResponseEntity<?> updateFunktion(@PathVariable(ApiNames.DECODER_ID) String decoderId,
                    @PathVariable(ApiNames.REIHE) Integer reihe, @PathVariable(ApiNames.FUNKTION) String funktion,
                    @RequestParam(ApiNames.BEZEICHNUNG) String bezeichnung) {
        return updated(service.updateFunktion(decoderId, reihe, funktion, bezeichnung));
    }
}
