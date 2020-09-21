package com.linepro.modellbahn.security.user;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.Optional;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.ApiPaths;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = ApiNames.USER)
@RequiredArgsConstructor
@RestController("User")
@ExposesResourceFor(UserModel.class)
public class UserController {

    @Autowired
    private final UserService userService;

    private final PagedResourcesAssembler<UserModel> assembler = new PagedResourcesAssembler<UserModel>(null, null);

    @GetMapping(path = ApiPaths.GET_USER, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds an User by name", description = "Finds a user", operationId = "get", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable(ApiNames.NAMEN) String name) {
        return userService.get(name)
                          .map(b -> ok(b))
                          .orElse(notFound().build());
    }

    @GetMapping(path = ApiPaths.SEARCH_USER, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds Users by example", description = "Finds users", operationId = "find", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserModel.class))) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> search(@RequestBody Optional<UserModel> model, @RequestParam(name = ApiNames.PAGE_NUMBER) Optional<Integer> pageNumber, @RequestParam(name = ApiNames.PAGE_SIZE) Optional<Integer> pageSize) {
        Page<UserModel> page = userService.search(model, pageNumber, pageSize);

        if (page.hasContent()) {
            return ok(
                assembler.toModel(
                    page.getPageable().isUnpaged() && page.hasContent() ?
                        new PageImpl<>(page.getContent(), page.getPageable(), page.getContent().size()) :
                        page,
                    it -> (RepresentationModel<?>) it
                )
            );
        }

        return notFound().build();
    }

    @PutMapping(path = ApiPaths.UPDATE_USER, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a User by name", description = "Update a User", operationId = "update", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(ApiNames.NAMEN) String name, @RequestBody UserModel model, Authentication authentication) {
        return userService.update(name, model, authentication)
                          .map(b -> accepted().body(b))
                          .orElse(notFound().build());
    }

    @PatchMapping(path = ApiPaths.CHANGE_PASSWORD)
    @Operation(summary = "Changes password for a named user", description = "Changes password for a user", operationId = "patch", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> changePassword(@PathVariable(ApiNames.NAMEN) String name, @RequestParam(ApiNames.PASSWORD) String password, Authentication authentication) {
        return status(ACCEPTED).body(userService.changePassword(name, password, authentication));
    }

    @DeleteMapping(path = ApiPaths.DELETE_USER)
    @Operation(summary = "Deletes a User by name", description = "Delete a User", operationId = "delete", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(ApiNames.NAMEN) String name, Authentication authentication) {
        return (userService.delete(name, authentication) ? noContent() : notFound()).build();
    }

    @PostMapping(path = ApiPaths.REGISTER_USER)
    @Operation(summary = "Registers a new user", description = "Registers a user", operationId = "post", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> register(@RequestBody UserModel model) {
        return status(CREATED).body(userService.register(model));
    }

    @PostMapping(path = ApiPaths.CONFIRM_USER)
    @Operation(summary = "Confirms a new user registration", description = "Confirms a new user registration", operationId = "post", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> confirm(@RequestParam(ApiNames.TOKEN) String token) {
        return status(ACCEPTED).body(userService.confirmRegistration(token));
    }

    @PostMapping(path = ApiPaths.FORGOT_PASSWORD)
    @Operation(summary = "Requests a password reset", description = "Requests a password reset", operationId = "post", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> forgot(@RequestParam(ApiNames.EMAIL) String email) {
        return status(ACCEPTED).body(userService.forgotPassword(email));
    }

    @PostMapping(path = ApiPaths.RESET_PASSWORD)
    @Operation(summary = "Resets a password reset", description = "Resets a password reset", operationId = "post", tags = { ApiNames.USER })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserMessage.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> resetPassword(@RequestParam(ApiNames.TOKEN) String token, @RequestParam(ApiNames.PASSWORD) String password) {
        return status(ACCEPTED).body(userService.resetPassword(token, password));
    }
}
