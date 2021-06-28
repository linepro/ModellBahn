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
import com.linepro.modellbahn.util.impexp.impl.SuppressExport;

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
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.TEIL_HERSTELLER, ApiNames.TEIL_BESTELL_NR, ApiNames.BEZEICHNUNG,
    ApiNames.KATEGORIE, ApiNames.UNTER_KATEGORIE, ApiNames.MENGE, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.TEILEN)
@Schema(name = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public class ProduktTeilModel extends SpringdocModel<ProduktTeilModel> implements ItemModel, Comparable<ProduktTeilModel> {

    private static final long serialVersionUID = 1L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.TEIL_HERSTELLER)
    @Schema(description = "Sub product Manufacturer", accessMode = AccessMode.READ_ONLY)
    private String teilHersteller;

    @JsonProperty(ApiNames.TEIL_BESTELL_NR)
    @Schema(description = "Sub product Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String teilBestellNr;

    @SuppressExport
    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @SuppressExport
    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category", example = "LOKOMOTIV", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @SuppressExport
    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Sub Category", example = "DAMPF", accessMode = AccessMode.READ_ONLY)
    private String unterKategorie;

    @JsonProperty(ApiNames.MENGE)
    @Schema(description = "Number included", example = "1", required = true)
    private Integer menge;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
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
