package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.REIHE, ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE, ApiNames.DELETED })
@Relation(collectionRelation = ApiNames.FUNKTION, itemRelation = ApiNames.FUNKTION)
@Schema(name = ApiNames.FUNKTION, description = "Decoder function mapping.")
public class DecoderFunktionModel extends RepresentationModel<DecoderFunktionModel> implements ItemModel, Comparable<DecoderFunktionModel> {

    private static final long serialVersionUID = -5315298133158215960L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(name = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY, required = true)
    private String decoderId;

    @JsonProperty(ApiNames.REIHE)
    @Schema(name = "Bank number (0-1) always 0 for single panel decoders", example = "0", required = true)
    private Integer reihe;

    @JsonProperty(ApiNames.FUNKTION)
    @Schema(name = "Function Key", example = "F0", required = true)
    private String funktion;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Usage", example = "Strinbeleuchtung", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.PROGRAMMABLE)
    @Schema(name = "True if this function can be reassigned", example = "false", required = true)
    private Boolean programmable;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
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
