package com.linepro.modellbahn.controller;

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
import com.linepro.modellbahn.configuration.UserMessage;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;
import com.linepro.modellbahn.controller.impl.NamedItemController;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.model.MotorTypModel.PagedMotorTypModel;
import com.linepro.modellbahn.service.impl.MotorTypService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * MotorTypService. CRUD service for MotorTyp
 * @author $Author:$
 * @version $Id:$
 */
@Tag(name = ApiNames.MOTOR_TYP)
@RestController("MotorTyp")
@ExposesResourceFor(MotorTypModel.class)
@SecurityRequirement(name = "BasicAuth")
public class MotorTypController extends NamedItemController<MotorTypModel> {

    private final MotorTypService service;

    @Autowired
    public MotorTypController(MotorTypService service) {
        super(service);

        this.service = service;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static MotorTypModel create() {
        return new MotorTypModel();
    }

    @Override
    @GetMapping(path = ApiPaths.GET_MOTOR_TYP, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Finds an MotorTyp by name", description = "Finds a motor type", operationId = "get", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "200", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = MotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return super.get(name);
    }

    @Override
    @GetMapping(path = ApiPaths.SEARCH_MOTOR_TYP, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Finds MotorTypen by example", description = "Finds UIC axle configurations", operationId = "find", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "200", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = PagedMotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> search(@RequestBody Optional<MotorTypModel> model,
                    @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber,
                    @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        return super.search(model, pageNumber, pageSize);
    }

    @Override
    @PostMapping(path = ApiPaths.ADD_MOTOR_TYP, consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Add a new MotorTyp", description = "Add a new UIC axle configuration", operationId = "add", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = MotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> add(@RequestBody MotorTypModel model) {
        return super.add(model);
    }

    @Override
    @PutMapping(path = ApiPaths.UPDATE_MOTOR_TYP, consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Updates an MotorTyp by name", description = "Update a motor type", operationId = "update", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = MotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody MotorTypModel model) {
        return super.update(name, model);
    }

    @Override
    @DeleteMapping(path = ApiPaths.DELETE_MOTOR_TYP)
    @Operation(summary = "Deletes an MotorTyp by name", description = "Delete a motor type", operationId = "delete", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name) {
        return super.delete(name);
    }

    @PutMapping(path = ApiPaths.ADD_MOTOR_TYP_ABBILDUNG, consumes = { MediaType.MULTIPART_FORM_DATA }, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Add an Motor Typ picture", description = "Adds or updates the picture of a named Motor Typ", operationId = "update", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = MotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Motor Typ not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> updateAbbildung(@PathVariable(ApiNames.NAMEN) String name, @RequestParam(ApiNames.ABBILDUNG) MultipartFile multipart) {
        return updated(service.updateAbbildung(name, multipart));
    }

    @DeleteMapping(path = ApiPaths.DELETE_MOTOR_TYP_ABBILDUNG, produces = { MediaType.APPLICATION_JSON, ApiPaths.APPLICATION_HAL_JSON })
    @Operation(summary = "Delete an Motor Typ picture", description = "Deletes the picture of a named Motor Typ", operationId = "update", tags = {
                    ApiNames.MOTOR_TYP
    }, responses = {
                    @ApiResponse(responseCode = "202", description = "Successful operation", content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = MotorTypModel.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Motor Typ not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)))
    })
    public ResponseEntity<?> deleteAbbildung(@PathVariable(ApiNames.NAMEN) String name) {
        return updated(service.deleteAbbildung(name));
    }
}
