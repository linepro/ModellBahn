package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.server.core.Relation;

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
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.REIHE, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.FUNKTION)
@Schema(name = ApiNames.FUNKTION, description = "Decoder function mapping.")
public class DecoderFunktionModel extends SpringdocModel<DecoderFunktionModel> implements ItemModel, Comparable<DecoderFunktionModel> {

    private static final long serialVersionUID = -5315298133158215960L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.REIHE)
    @Schema(description = "Bank number (0-1) always 0 for single panel decoders", example = "0", accessMode = AccessMode.READ_ONLY)
    private Integer reihe;

    @JsonProperty(ApiNames.FUNKTION)
    @Schema(description = "Function Key", example = "F0", accessMode = AccessMode.READ_ONLY)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.PROGRAMMABLE)
    @Schema(description = "True if this function can be reassigned", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean programmable;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderFunktionModel other) {
        return new CompareToBuilder()
            .append(decoderId, other.decoderId)
            .append(reihe, other.reihe)
            .append(funktion, other.funktion)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(decoderId)
            .append(reihe)
            .append(funktion)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderFunktionModel)) {
            return false; 
        }

        DecoderFunktionModel other = (DecoderFunktionModel) obj;

        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .append(reihe, other.reihe)
                .append(funktion, other.funktion)
                .isEquals();
    }
}
