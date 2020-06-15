package com.linepro.modellbahn.util.impexp;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = ApiNames.DATA)
@RestController
@RequiredArgsConstructor
public class DataController {

    @Autowired
    private final DataService service;
   
    @GetMapping(ApiPaths.EXPORT)
    @Produces(DataService.TEXT_CSV)
    @Operation(summary = "Export data as CSV", description = "Finds an UIC axle configuration", operationId = "get", tags = { "Achsfolg" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "text/csv") }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public void exportCSV(@PathVariable(ApiNames.DATA_TYPE) DataType type, HttpServletResponse response) throws IOException {
        try {
            service.exportCSV(type, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping(ApiPaths.IMPORT)
    @Consumes(DataService.TEXT_CSV)
    @Operation(summary = "Add an Aufbau picture", description = "Adds or updates the picture of a named Aufbau", operationId = "update", tags = { "Aufbau" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aufbau not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> importCSV(@PathVariable(ApiNames.DATA_TYPE) DataType type, @PathVariable("file") MultipartFile multipart) {
        try {
            service.importCSV(type, multipart);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        return ResponseEntity.ok().build();
    }

}
