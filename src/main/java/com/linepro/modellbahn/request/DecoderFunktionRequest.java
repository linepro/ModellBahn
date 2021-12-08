package com.linepro.modellbahn.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
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

/**
 * IDecoderFunktion.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.FUNKTION)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED })
@Schema(name = ApiNames.FUNKTION, description = "Decoder function mapping.")
public class DecoderFunktionRequest implements ItemRequest {

    /**
     * 
     */
    private static final long serialVersionUID = 8189766846221554166L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.FUNKTION)
    @Schema(description = "Function Key", example = "F0", accessMode = AccessMode.READ_ONLY)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(decoderId)
            .append(funktion)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderFunktionRequest)) {
            return false; 
        }

        DecoderFunktionRequest other = (DecoderFunktionRequest) obj;

        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .append(funktion, other.funktion)
                .isEquals();
    }
}
