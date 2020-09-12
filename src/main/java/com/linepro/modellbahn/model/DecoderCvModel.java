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
 * IDecoderCv.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CV)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.CV, ApiNames.BEZEICHNUNG, ApiNames.MINIMAL, ApiNames.MAXIMAL, ApiNames.WERKSEINSTELLUNG, ApiNames.WERT, ApiNames.DELETED })
@Relation(collectionRelation = ApiNames.CV, itemRelation = ApiNames.CV)
@Schema(name = ApiNames.CV, description = "Decoder CV setting.")
public class DecoderCvModel extends RepresentationModel<DecoderCvModel> implements ItemModel, Comparable<DecoderCvModel> {

    private static final long serialVersionUID = 6780491207710890606L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.CV)
    @Schema(description = "CV number", example = "63", accessMode = AccessMode.READ_ONLY)
    private Integer cv;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "CV usage", example = "Geräuschlautstärke", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @JsonProperty(ApiNames.MINIMAL)
    @Schema(description = "Minimum value", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer minimal;

    @JsonProperty(ApiNames.MAXIMAL)
    @Schema(description = "Maximum value", example = "63", accessMode = AccessMode.READ_ONLY)
    private Integer maximal;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @Schema(description = "Default value", example = "63", accessMode = AccessMode.READ_ONLY)
    private Integer werkseinstellung;

    @JsonProperty(ApiNames.WERT)
    @Schema(description = "Assigned value", required = true)
    private Integer wert;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderCvModel other) {
        return new CompareToBuilder()
            .append(decoderId, other.decoderId)
            .append(cv, other.cv)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(decoderId)
            .append(cv)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderCvModel)) {
            return false; 
        }

        DecoderCvModel other = (DecoderCvModel) obj;
        
        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .append(cv, other.cv)
                .isEquals();
    }
}
