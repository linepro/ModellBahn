package com.linepro.modellbahn.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
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
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.ZUG, ApiNames.ZUG_BEZEICHNUNG, ApiNames.POSITION, ApiNames.ARTIKEL_ID, ApiNames.ARTIKEL_BEZEICHNUNG,
    ApiNames.HERSTELLER, ApiNames.HERSTELLER_BEZEICHNUNG, ApiNames.BESTELL_NR, ApiNames.KATEGORIE, ApiNames.KATEGORIE_BEZEICHNUNG,
    ApiNames.UNTER_KATEGORIE, ApiNames.UNTER_KATEGORIE_BEZEICHNUNG, ApiNames.LANGE, ApiNames.BAHNVERWALTUNG, 
    ApiNames.BAHNVERWALTUNG_BEZEICHNUNG, ApiNames.GATTUNG, ApiNames.GATTUNG_BEZEICHNUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.FAHRZEUGEN)
@Schema(name = ApiNames.CONSIST, description = "Rolling stock by poisition in a train.")
public class ZugConsistModel extends SpringdocModel<ZugConsistModel> implements ItemModel, Comparable<ZugConsistModel> {

    private static final long serialVersionUID = -400353674903033600L;

    @JsonProperty(ApiNames.ZUG)
    @Schema(description = "Train code", example = "BAVARIA", accessMode = AccessMode.READ_ONLY)
    private String zug;

    @SuppressExport
    @JsonProperty(ApiNames.ZUG_BEZEICHNUNG)
    @Schema(description = "Train code", example = "BAVARIA", accessMode = AccessMode.READ_ONLY)
    private String zugBezeichnung;

    @JsonProperty(ApiNames.POSITION)
    @Schema(description = "Contiguous 1 based position in the train (1 = head)", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer position;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", required = true)
    private String artikelId;

    @SuppressExport
    @JsonProperty(ApiNames.ARTIKEL_BEZEICHNUNG)
    @Schema(description = "Artikel id", example = "00001", required = true)
    private String artikelBezeichnung;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @SuppressExport
    @JsonProperty(ApiNames.HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String herstellerBezeichnung;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @SuppressExport
    @JsonProperty(ApiNames.KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorieBezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorie;

    @SuppressExport
    @JsonProperty(ApiNames.UNTER_KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorieBezeichnung;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal lange;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", accessMode = AccessMode.READ_ONLY)
    private String bahnverwaltung;

    @SuppressExport
    @JsonProperty(ApiNames.BAHNVERWALTUNG_BEZEICHNUNG)
    @Schema(description = "Railway company", accessMode = AccessMode.READ_ONLY)
    private String bahnverwaltungBezeichnung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", accessMode = AccessMode.READ_ONLY)
    private String gattung;

    @SuppressExport
    @JsonProperty(ApiNames.GATTUNG_BEZEICHNUNG)
    @Schema(description = "Vehicle class", accessMode = AccessMode.READ_ONLY)
    private String gattungBezeichnung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006", accessMode = AccessMode.READ_ONLY)
    private String betreibsnummer;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/artikel/00001/abbildung.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @JsonIgnore
    private boolean last;

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