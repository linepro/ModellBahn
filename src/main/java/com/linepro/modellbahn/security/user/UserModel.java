package com.linepro.modellbahn.security.user;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.USER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.EMAIL, ApiNames.FIRST_NAME, ApiNames.LAST_NAME, ApiNames.LOCALE, ApiNames.PASSWORD, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.USER, itemRelation = ApiNames.USER)
@Schema(name = ApiNames.USER, description = "System User")
public class UserModel extends RepresentationModel<UserModel> {

    @JsonProperty(ApiNames.NAMEN)
    @Schema(description = "Screen Name", example = "linepro", accessMode = AccessMode.READ_ONLY)
    private String name;

    @JsonProperty(ApiNames.EMAIL)
    @Schema(description = "eMail address", example = "linepro@compuserve.com")
    private String email;

    @JsonProperty(ApiNames.FIRST_NAME)
    @Schema(description = "First Name", example = "John")
    private String firstName;

    @JsonProperty(ApiNames.LAST_NAME)
    @Schema(description = "Surname", example = "Goff")
    private String lastName;

    @JsonProperty(ApiNames.LOCALE)
    @Schema(description = "Locale", example = "en_GB")
    private String locale;

    @JsonProperty(ApiNames.PASSWORD)
    @Schema(description = "Password, encrypted", example = "Pa$5w0rd", accessMode = AccessMode.WRITE_ONLY)
    private String password;

    @JsonProperty(ApiNames.ENABLED)
    @Schema(description = "Password, encrypted", example = "Pa$5w0rd", accessMode = AccessMode.READ_ONLY)
    private Boolean enabled;

    @JsonProperty(ApiNames.LAST_LOGIN)
    @Schema(description = "Password, encrypted", example = "Pa$5w0rd", accessMode = AccessMode.READ_ONLY)
    private LocalDateTime lastLogin;
}