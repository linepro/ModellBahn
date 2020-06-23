package com.linepro.modellbahn.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

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
 * IZugConsist.
 * @author   $Author$
 * @version  $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.CONSIST)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.POSITION, ApiNames.ARTIKEL, ApiNames.DELETED })
@Schema(name = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public class ZugConsistModel extends RepresentationModel<ZugConsistModel> implements ItemModel, Comparable<ZugConsistModel> {

    private static final long serialVersionUID = -400353674903033600L;

    @JsonProperty(ApiNames.ZUG)
    @Schema(name = "Train code", example = "BAVARIA", required = true)
    private String zug;

    @JsonProperty(ApiNames.POSITION)
    @Schema(name = "Contiguous 1 based position in the train (1 = head)", example = "1", accessMode = AccessMode.READ_ONLY, required = true)
    private Integer position;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(name = "Artikel id", example = "00001", required = true)
    private String artikelId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(name = "Manufacturer", example = "Marklin", required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(name = "Part number", example = "3000", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.LANGE)
    @Schema(name = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(name = "Railway company")
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(name = "Vehicle class")
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(implementation = String.class, name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(ZugConsistModel other) {
        return new CompareToBuilder()
            .append(zug, other.zug)
            .append(position, other.position)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(zug)
            .append(position)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ZugConsistModel)) {
            return false; 
        }

        ZugConsistModel other = (ZugConsistModel) obj;
        
        return new EqualsBuilder()
                .append(zug, other.zug)
                .append(position, other.position)
                .isEquals();
    }
}