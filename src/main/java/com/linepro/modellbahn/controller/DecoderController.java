package com.linepro.modellbahn.controller;

import static org.springframework.http.ResponseEntity.of;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.request.DecoderRequest;
import com.linepro.modellbahn.service.criterion.DecoderCriterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;
import com.linepro.modellbahn.service.impl.DecoderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.DECODER)
@RestController("Decoder")
@ExposesResourceFor(DecoderModel.class)
public class DecoderController extends AbstractItemController<DecoderModel, DecoderRequest> {

    private final DecoderService service;

    @Autowired
    public DecoderController(DecoderService service) {
        super(service);

        this.service = service;
    }

    @GetMapping(path = ApiPaths.GET_DECODER, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds an Decoder by id", description = "Finds a decoder", operationId = "get", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.DECODER_ID) String decoderId) {
        return of(service.get(decoderId));
    }

    @Schema(name = ApiNames.DECODER + "Page")
    class PagedDecoderModel extends PagedSchema<DecoderModel>{}

    @GetMapping(path = ApiPaths.SEARCH_DECODER, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds Decoders by example", description = "Finds decoders", operationId = "find", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedDecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(DecoderCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @PostMapping(path = ApiPaths.ADD_DECODER, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds an Decoder by Typ", description = "Add a decoder", operationId = "add", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> addDecoder(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderRequest request) {
        return added(service.add(herstellerStr, bestellNr, request));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an Decoder by id", description = "Update a decoder", operationId = "update", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.DECODER_ID) String decoderId, @RequestBody DecoderRequest request) {
        return updated(service.update(decoderId, request));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER)
    @Operation(summary = "Deletes an Decoder by id", description = "Delete a decoder", operationId = "delete", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.DECODER_ID) String decoderId) {
        return deleted(service.delete(decoderId));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_ADRESS, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates a decoder address", description = "", operationId = "update", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderAdressModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiNames.DECODER_ID) String decoderId, @PathVariable(ApiNames.INDEX) Integer index, @RequestParam(ApiNames.ADRESS) Integer adress) {
        return updated(service.updateAdress(decoderId, index, adress));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_CV, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates a decoder cv", description = "", operationId = "update", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderCvModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> updateCv(@PathVariable(ApiNames.DECODER_ID) String decoderId, @PathVariable(ApiNames.CV) Integer cv, @RequestParam(ApiNames.WERT) Integer wert) {
        return updated(service.updateCv(decoderId, cv, wert));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_FUNKTION, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates a decoder function", description = "", operationId = "update", tags = { ApiNames.DECODER }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderFunktionModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
   public ResponseEntity<?> updateFunktion(@PathVariable(ApiNames.DECODER_ID) String decoderId,
                    @PathVariable(ApiNames.REIHE) Integer reihe, @PathVariable(ApiNames.FUNKTION) String funktion,
                    @RequestParam(ApiNames.BEZEICHNUNG) String bezeichnung) {
        return updated(service.updateFunktion(decoderId, reihe, funktion, bezeichnung));
    }
}
