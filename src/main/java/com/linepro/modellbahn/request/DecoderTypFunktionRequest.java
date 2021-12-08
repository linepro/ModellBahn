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
 * IDecoderTypFunktion.
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
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED })
@Schema(name = ApiNames.FUNKTION, description = "Decoder type function mapping - template for Decoder.")
public class DecoderTypFunktionRequest implements ItemRequest {

    /**
     * 
     */
    private static final long serialVersionUID = -5772577653743152959L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.FUNKTION)
    @Schema(description = "Function Key", example = "F0", required = true)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.PROGRAMMABLE)
    @Schema(description = "True if this function can be reassigned", example = "false", required = true)
    private Boolean programmable;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(hersteller)
            .append(bestellNr)
            .append(funktion)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderTypFunktionRequest)) {
            return false; 
        }

        DecoderTypFunktionRequest other = (DecoderTypFunktionRequest) obj;

        return new EqualsBuilder()
                .append(hersteller, other.hersteller)
                .append(bestellNr, other.bestellNr)
                .append(funktion, other.funktion)
                .isEquals();
    }
}
