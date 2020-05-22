package com.linepro.modellbahn.model;

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
 * ProduktDtoTeil.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.TEIL)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.PRODUKT, ApiNames.TEIL, ApiNames.ANZAHL, ApiNames.DELETED})
@Schema(name = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public class ProduktTeilModel extends RepresentationModel<ProduktTeilModel> implements ItemModel, Comparable<ProduktTeilModel> {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", accessMode = AccessMode.READ_ONLY, required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(name = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY, required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.TEIL_HERSTELLER)
    @Schema(implementation = ProduktModel.class, name = "Sub product Manufacturer", accessMode = AccessMode.READ_ONLY, required = true)
    private String teilHersteller;

    @JsonProperty(ApiNames.TEIL_BESTELL_NR)
    @Schema(name = "Sub product Part number", example = "3000", accessMode = AccessMode.READ_ONLY, required = true)
    private String teilBestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Description", example = "Dampftenderlok BR 89.0", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    private UnterKategorieModel unterKategorie;

    @JsonProperty(ApiNames.ANZAHL)
    @Schema(name = "Number included", example = "1", required = true)
    private Integer anzahl;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(ProduktTeilModel other) {
        return new CompareToBuilder()
            .append(hersteller, other.hersteller)
            .append(bestellNr, other.bestellNr)
            .append(teilHersteller, other.hersteller)
            .append(teilBestellNr, other.teilBestellNr)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(hersteller)
            .append(bestellNr)
            .append(teilHersteller)
            .append(teilBestellNr)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProduktTeilModel)) {
            return false; 
        }

        ProduktTeilModel other = (ProduktTeilModel) obj;
        
        return new EqualsBuilder()
                .append(hersteller, other.hersteller)
                .append(bestellNr, other.bestellNr)
                .append(teilHersteller, other.teilHersteller)
                .append(teilBestellNr, other.teilBestellNr)
                .isEquals();
    }
}
