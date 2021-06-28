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
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
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
 * IDecoderTypCV.
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
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.CV, ApiNames.BEZEICHNUNG, ApiNames.MINIMAL, ApiNames.MAXIMAL,
    ApiNames.WERKSEINSTELLUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.CV)
@Schema(name = ApiNames.CV, description = "Decoder type CV - template for Decoder.")
public class DecoderTypCvModel extends SpringdocModel<DecoderTypCvModel> implements ItemModel, Comparable<DecoderTypCvModel> {

    private static final long serialVersionUID = -8055283388490564394L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.CV)
    @Schema(description = "CV number", example = "63", required = true)
    private Integer cv;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "CV usage", example = "Geräuschlautstärke", required = true)
    private String bezeichnung;

    @JsonProperty(ApiNames.MINIMAL)
    @Schema(description = "Minimum value", example = "1")
    private Integer minimal;

    @JsonProperty(ApiNames.MAXIMAL)
    @Schema(description = "Maximum value", example = "63")
    private Integer maximal;

    @JsonProperty(ApiNames.WERKSEINSTELLUNG)
    @Schema(description = "Default value", example = "63", required = true)
    private Integer werkseinstellung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderTypCvModel other) {
        return new CompareToBuilder()
            .append(hersteller, other.hersteller)
            .append(bestellNr, other.bestellNr)
            .append(cv, other.cv)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(hersteller)
            .append(bestellNr)
            .append(cv)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderTypCvModel)) {
            return false; 
        }

        DecoderTypCvModel other = (DecoderTypCvModel) obj;

        return new EqualsBuilder()
                .append(hersteller, other.hersteller)
                .append(bestellNr, other.bestellNr)
                .append(cv, other.cv)
                .isEquals();
    }
}
