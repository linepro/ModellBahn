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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.linepro.modellbahn.controller.impl.AbstractItemController;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
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
@RestController("ProduktController")
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
    
    @GetMapping(path = ApiPaths.GET_PRODUKT, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an Produkt by name", description = "Finds a product", operationId = "get", tags = { "Produkt" })
        @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProduktModel.class)) }),
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
    @GetMapping(path = ApiPaths.SEARCH_PRODUKT, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Produkten by example", description = "Finds products", operationId = "find", tags = { "Produkt" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProduktModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found, content = @Content"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<ProduktModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_PRODUKT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
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

    @PutMapping(path = ApiPaths.UPDATE_PRODUKT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> update(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody ProduktModel model) {
        return updated(service.update(herstellerStr, bestellNr, model));
    }

    @DeleteMapping(path = ApiPaths.DELETE_PRODUKT)
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
    public ResponseEntity<?> delete(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) {
        return deleted(service.delete(herstellerStr, bestellNr));
    }

    @PostMapping(path = ApiPaths.ADD_PRODUKT_TEIL, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> addTeil(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestBody ProduktTeilModel teil) {
        return added(service.addTeil(herstellerStr, bestellNr, teil.getTeilHersteller(), teil.getTeilBestellNr(), teil.getAnzahl()));
    }

    @PutMapping(path = ApiPaths.UPDATE_PRODUKT_TEIL, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> updateTeil(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @PathVariable(ApiNames.TEIL_BESTELL_NR) String teilBestellNr, @RequestParam(ApiNames.ANZAHL) Integer anzahl) {
        return updated(service.updateTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr, anzahl));
    }

    @DeleteMapping(path = ApiPaths.DELETE_PRODUKT_TEIL)
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
    public ResponseEntity<?> deleteTeil(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @PathVariable(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @PathVariable(ApiNames.TEIL_BESTELL_NR) String teilBestellNr) {
        return deleted(service.deleteTeil(herstellerStr, bestellNr, teilHerstellerStr, teilBestellNr));
    }

    @PutMapping(path = ApiPaths.ADD_PRODUKT_ABBILDUNG, consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr, @RequestParam("abbildung") MultipartFile multipart) throws Exception {
        return updated(service.updateAbbildung(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_PRODUKT_ABBILDUNG, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) throws Exception {
        return updated(service.deleteAbbildung(herstellerStr, bestellNr));
    }

    @PutMapping(path = ApiPaths.ADD_PRODUKT_ANLEITUNGEN, consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> updateAnleitungen(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @RequestParam("anleitungen") MultipartFile multipart) throws Exception {
        return updated(service.updateAnleitungen(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_PRODUKT_ANLEITUNGEN, produces = MediaType.APPLICATION_JSON)
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

    @PutMapping(path = ApiPaths.ADD_PRODUKT_EXPLOSIONSZEICHNUNG, consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> updateExplosionszeichnung(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr,
            @RequestParam("explosionszeichnung") MultipartFile multipart) throws Exception {
        return updated(service.updateExplosionszeichnung(herstellerStr, bestellNr, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_PRODUKT_EXPLOSIONSZEICHNUNG, produces = MediaType.APPLICATION_JSON)
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
    public ResponseEntity<?> deleteExplosionszeichnung(@PathVariable(ApiNames.HERSTELLER) String herstellerStr, @PathVariable(ApiNames.BESTELL_NR) String bestellNr) throws Exception {
        return updated(service.deleteExplosionszeichnung(herstellerStr, bestellNr));
    }
}
