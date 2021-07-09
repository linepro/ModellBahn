package com.linepro.modellbahn.util.impexp;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = ApiNames.DATA)
@RestController("Data")
@RequiredArgsConstructor
@SecurityRequirement(name = "BasicAuth")
public class DataController {

    private static final String FILE_FIELD = "data";
    @Autowired
    private final DataService service;

    @GetMapping(path = ApiPaths.EXPORT, produces = { DataService.TEXT_CSV })
    @Operation(summary = "Export data as CSV", description = "Exports the type as a CSV file", operationId = "get", tags = { ApiNames.DATA }, responses = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "text/csv") }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public void exportCSV(@PathVariable(ApiNames.DATA_TYPE) String type, HttpServletResponse response) {
        service.exportCSV(type, response);
    }

    @PostMapping(path = ApiPaths.IMPORT, consumes = { MediaType.MULTIPART_FORM_DATA })
    @Operation(summary = "Import data from CSV", description = "Imports the type from a CSV file", operationId = "update", tags = { ApiNames.DATA }, responses = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> importCSV(@PathVariable(ApiNames.DATA_TYPE) String type, @RequestParam(FILE_FIELD) MultipartFile multipart) {
        service.importCSV(type, multipart);

        return ResponseEntity.ok().build();
    }

}
