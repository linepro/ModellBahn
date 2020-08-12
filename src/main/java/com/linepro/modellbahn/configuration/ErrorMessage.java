package com.linepro.modellbahn.configuration;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ "timestamp", "status", "error", "message", "path", "developerMessage", "moreInfo" })
@Schema(description = "Error Messages")
public class ErrorMessage {

    @JsonProperty("timestamp")
    @Schema(description = "Timestamp", example = "1593346288902", accessMode = AccessMode.READ_ONLY)
    private long timestamp;

    @JsonProperty("status")
    @Schema(description = "Status", example = "403", accessMode = AccessMode.READ_ONLY)
    private int status;
    
    @JsonProperty("error")
    @Schema(description = "Error", example = "Forbidden", accessMode = AccessMode.READ_ONLY)
    private String error;

    @JsonProperty("message")
    @Schema(description = "Message for user consumption", example = "Forbidden", accessMode = AccessMode.READ_ONLY)
    private String message;

    @JsonProperty("path")
    @Schema(description = "Request path", example = "Forbidden", accessMode = AccessMode.READ_ONLY)
    private String path;

    @JsonProperty("developerMessage")
    @Schema(description = "Extended message for debugging", example = "partial stack trace", accessMode = AccessMode.READ_ONLY)
    private String developerMessage;

    @JsonProperty("moreInfo")
    @Schema(description = "URL for extended information", accessMode = AccessMode.READ_ONLY)
    private String moreInfo;
}
