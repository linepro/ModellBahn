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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.service.impl.ProduktService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.PRODUKT)
@RestController
@RequestMapping(ApiPaths.PRODUKT)
@ExposesResourceFor(ProduktModel.class)
public class ProduktController extends AbstractItemController<ProduktModel> {

    private final ProduktService service;
     
    @Autowired
    public ProduktController(ProduktService service) {
        super(service);

        this.service = service;
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
    @Operation(summary = "Finds an Produkt by name", description = "Finds a product", operationId = "get", tags = { "Produkt" })
        @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) {
        logGet(herstellerStr, bestellNr);

        return of(service.get(herstellerStr, bestellNr));
    }

    @Override
    @GetMapping(ApiPaths.SEARCH)
    @JsonView(Views.DropDown.class)
    @Operation(summary = "Finds Produkten by example", description = "Finds products", operationId = "find", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProduktModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody ProduktModel model, @RequestParam(name = ApiNames.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE, required = false) Integer pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(ApiPaths.ADD)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add a new Produkt", description = "Add a new product", operationId = "add", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> add(@RequestBody ProduktModel model) {
        return super.add(model);
    }

    @PutMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates an Produkt by name", description = "Update an Product", operationId = "update", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, ProduktModel model) {
        logPut(herstellerStr, bestellNr);

        return of(service.update(herstellerStr, bestellNr, model));
    }

    @DeleteMapping(ApiPaths.PRODUKT_PART)
    @JsonView(Views.Public.class)
    @Operation(summary = "Deletes an Produkt by name", description = "Delete an Product", operationId = "update", tags = { "Produkt" })
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

    @PostMapping(ApiPaths.PRODUKT_TEIL_ROOT)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds a component of a Produkt", description = "", operationId = "", tags = { "ProduktTeil" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktTeilModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> addTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, ProduktTeilModel teil) {
        logPost(String.format(ApiPaths.PRODUKT_TEIL_ROOT_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + teil);

        return of(service.addTeil(herstellerStr, bestellNr, teil));
    }

    @PutMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Updates a component of a Produkt", description = "", operationId = "", tags = { "ProduktTeil" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktTeilModel.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> updateTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr, ProduktTeilModel teil) {
        logPut(String.format(ApiPaths.PRODUKT_TEIL_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr) + ":" + teil);

        return of(service.updateTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, teil));
    }

    @DeleteMapping(ApiPaths.PRODUKT_TEIL_PATH)
    @JsonView(Views.Public.class)
    @Operation(summary = "Removes a component of a Produkt", description = "", operationId = "", tags = { "ProduktTeil" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteTeil(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable(ApiPaths.TEIL_HERSTELLER_PARAM_NAME) String teilHerstellerStr, @PathVariable(ApiPaths.TEIL_BESTELL_NR_PARAM_NAME) String teilBestellNr) {
        logDelete(String.format(ApiPaths.PRODUKT_TEIL_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));

        return (service.deleteTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr) ? noContent() : notFound()).build();
    }

    @PutMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @Operation(summary = "Add an Produkt picture", description = "Adds or updates the picture of a named Produkt", operationId = "update", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr, @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        return of(service.updateAbbildung(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(ApiPaths.PRODUKT_ABBILDUNG)
    @JsonView(Views.Public.class)
    @Operation(summary = "Removes a picture from an Product", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_ABBILDUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));

        return of(service.deleteAbbildung(herstellerStr, bestellNr));
    }

    @PutMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds Produkt instructions", description = "Adds or updates the instructions for a named Produkt", operationId = "update", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        return of(service.updateAnleitungen(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(ApiPaths.PRODUKT_ANLEITUNGEN)
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
    public ResponseEntity<?> deleteAnleitungen(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_ANLEITUNGEN_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));

        return of(service.deleteAnleitungen(herstellerStr, bestellNr));
    }

    @PutMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @Operation(summary = "Adds Produkt parts diagram", description = "Adds or updates the picture of a named Produkt", operationId = "update", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> updateExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr,
            @PathVariable("file") MultipartFile multipart) throws Exception {
        logPut(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr) + ": " + multipart.getOriginalFilename());

        return of(service.updateExplosionszeichnung(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG)
    @JsonView(Views.Public.class)
    @Operation(summary = "Removes a Produkt parts diagram", description = "", operationId = "", tags = { "" })
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
                })
    public ResponseEntity<?> deleteExplosionszeichnung(@PathVariable(ApiPaths.HERSTELLER_PARAM_NAME) String herstellerStr, @PathVariable(ApiPaths.BESTELL_NR_PARAM_NAME) String bestellNr) throws Exception {
        logDelete(String.format(ApiPaths.PRODUKT_EXPLOSIONSZEICHNUNG_LOG, ApiNames.PRODUKT, herstellerStr, bestellNr));

        return of(service.deleteExplosionszeichnung(herstellerStr, bestellNr));
    }
}
