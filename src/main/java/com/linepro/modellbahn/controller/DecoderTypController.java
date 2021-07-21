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
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.request.DecoderTypAdressRequest;
import com.linepro.modellbahn.request.DecoderTypCvRequest;
import com.linepro.modellbahn.request.DecoderTypFunktionRequest;
import com.linepro.modellbahn.request.DecoderTypRequest;
import com.linepro.modellbahn.service.criterion.DecoderTypCriterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;
import com.linepro.modellbahn.service.impl.DecoderTypService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * DecoderTypService. CRUD service for DecoderTyp, DecoderTypCv and DecoderTypFunktion
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.DECODER_TYP)
@RestController("DecoderTyp")
@ExposesResourceFor(DecoderTypModel.class)
@SecurityRequirement(name = "BasicAuth")
public class DecoderTypController extends AbstractItemController<DecoderTypModel, DecoderTypRequest> {

    private final DecoderTypService service;

    @Autowired
    public DecoderTypController(DecoderTypService service) {
        super(service);

        this.service = service;
    }

    @GetMapping(path = ApiPaths.GET_DECODER_TYP, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds an DecoderTyp by name", description = "Finds a train", operationId = "get", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return of(service.get(herstellerStr, bestellNr));
    }

    @Schema(name = ApiNames.DECODER_TYP + "Page")
    class PagedDecoderTypModel extends PagedSchema<DecoderTypModel>{}

    @GetMapping(path = ApiPaths.SEARCH_DECODER_TYP, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Finds DecoderTypen by example", description = "Finds train configurations", operationId = "find", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PagedDecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(DecoderTypCriterion request, PageCriteria page) {
        return super.search(request, page);
    }

    @PostMapping(path = ApiPaths.ADD_DECODER_TYP, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds an DecoderTyp", description = "Update a train", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody DecoderTypRequest request) {
        return super.add(request);
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_TYP, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates an DecoderTyp by name", description = "Update a train", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypRequest request) {
        return updated(service.update(herstellerStr, bestellNr, request));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER_TYP)
    @Operation(summary = "Deletes an DecoderTyp by name", description = "Delete a train", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return deleted(service.delete(herstellerStr, bestellNr));
    }

    @PostMapping(path = ApiPaths.ADD_DECODER_TYP_ADRESS, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds an Adress to a DecoderTyp", description = "", operationId = "add", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypAdressModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })

    public ResponseEntity<?> addAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypAdressRequest request) {
            return added(service.addAdress(herstellerStr, bestellNr, request));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_TYP_ADRESS, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Update an Adress of a DecoderTyp", description = "", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypAdressModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> updateAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.INDEX) Integer index,
                    @RequestBody DecoderTypAdressRequest request) {
        return updated(service.updateAdress(herstellerStr, bestellNr, index, request));
   }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER_TYP_ADRESS)
    @Operation(summary = "Removes an Adress from a DecoderTyp", description = "", operationId = "delete", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> deleteAdress(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.INDEX) Integer index) {
        return deleted(service.deleteAdress(herstellerStr, bestellNr, index));
    }

    @PostMapping(path = ApiPaths.ADD_DECODER_TYP_CV, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds a CV to a DecoderTyp", description = "", operationId = "add", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypCvModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
     public ResponseEntity<?> addCV(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypCvRequest request) {
            return added(service.addCv(herstellerStr, bestellNr, request));
     }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_TYP_CV, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Updates a CV of a DecoderTyp", description = "", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypCvModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> updateCV(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.CV) Integer cv,
                    @RequestBody DecoderTypCvRequest request) {
        return updated(service.updateCv(herstellerStr, bestellNr, cv, request));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER_TYP_CV)
    @Operation(summary = "Removes a CV from a DecoderTyp", description = "", operationId = "delete", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> deleteCV(
            @PathVariable(ApiNames.HERSTELLER) String herstellerStr,
            @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @PathVariable(ApiNames.CV) Integer cv) {
        return deleted(service.deleteCv(herstellerStr, bestellNr, cv));
    }

    @PostMapping(path = ApiPaths.ADD_DECODER_TYP_FUNKTION, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds a Funktion to a DecoderTyp", description = "", operationId = "add", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypFunktionModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
     public ResponseEntity<?> addFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody DecoderTypFunktionRequest request) {
        return added(service.addFunktion(herstellerStr, bestellNr, request));
    }

    @PutMapping(path = ApiPaths.UPDATE_DECODER_TYP_FUNKTION, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Update a Funktion of a DecoderTyp", description = "", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = DecoderTypFunktionModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> updateFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.REIHE) Integer reihe,
                    @PathVariable(ApiNames.FUNKTION) String funktion, @RequestBody DecoderTypFunktionRequest request) {
        return updated(service.updateFunktion(herstellerStr, bestellNr, reihe, funktion, request));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER_TYP_FUNKTION)
    @Operation(summary = "Removes a Funktion from a DecoderTyp", description = "", operationId = "delete", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> deleteFunktion(@PathVariable(ApiNames.HERSTELLER) String herstellerStr,
                    @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.REIHE) Integer reihe,
                    @PathVariable(ApiNames.FUNKTION) String funktion) {
        return deleted(service.deleteFunktion(herstellerStr, bestellNr, reihe, funktion));
    }

    @PutMapping(path = ApiPaths.ADD_DECODER_TYP_ANLEITUNGEN, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Adds DecoderTyp instructions", description = "Adds or updates the instructions for a DecoderTyp", operationId = "update", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> updateAnleitungen(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @RequestParam(ApiNames.ANLEITUNGEN) MultipartFile multipart) {
        return updated(service.updateAnleitungen(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_DECODER_TYP_ANLEITUNGEN, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
    @Operation(summary = "Removes instructions from a DecoderTyp", description = "", operationId = "delete", tags = { ApiNames.DECODER_TYP }, responses = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
                })
    public ResponseEntity<?> deleteAnleitungen(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return updated(service.deleteAnleitungen(herstellerStr, bestellNr));
    }

}
