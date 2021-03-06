package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.linepro.modellbahn.model.enums.Status;
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
 * ArtikelModel.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.ARTIKEL)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.ARTIKEL_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.KATEGORIE, ApiNames.KAUFDATUM, 
    ApiNames.UNTER_KATEGORIE, ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.GATTUNG,
    ApiNames.BETREIBSNUMMER, ApiNames.ACHSFOLG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG,
    ApiNames.DECODER, ApiNames.MOTOR_TYP, ApiNames.KAUFDATUM, ApiNames.WAHRUNG, ApiNames.PREIS, ApiNames.STUCK, ApiNames.VERBLEIBENDE, ApiNames.ANMERKUNG,
    ApiNames.BELADUNG, ApiNames.STATUS, ApiNames.ANDERUNGEN, ApiNames.ABBILDUNG, ApiNames.GROSSANSICHT, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.ARTIKEL)
@Schema(name = ApiNames.ARTIKEL, description = "An article - may differ from product because of modificiations")
public class ArtikelModel extends RepresentationModel<ArtikelModel> implements ItemModel, Comparable<ArtikelModel> {

    private static final long serialVersionUID = 3146760791932382500L;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String artikelId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA", example = "IV", accessMode = AccessMode.READ_ONLY)
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", example = "DB", accessMode = AccessMode.READ_ONLY)
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0", accessMode = AccessMode.READ_ONLY)
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration", example = "CH2T", accessMode = AccessMode.READ_ONLY)
    private String achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI", accessMode = AccessMode.READ_ONLY)
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", accessMode = AccessMode.READ_ONLY)
    private String aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light Configuration", example = "L1V")
    private String licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerung;

    @JsonProperty(ApiNames.DECODER)
    @Schema(description = "Decoder", example = "1")
    private String decoder;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

    @JsonProperty(ApiNames.KAUFDATUM)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Purchase date", example = "1967-08-10")
    private LocalDate kaufdatum;

    @JsonProperty(ApiNames.WAHRUNG)
    @Schema(description = "Purchase currency, ISO 4217 code", example = "EUR")
    private String wahrung;

    @JsonProperty(ApiNames.PREIS)
    @Schema(description = "Purchase price", example = "115.95")
    private BigDecimal preis;

    @JsonProperty(ApiNames.STUCK)
    @Schema(description = "Purchase Quantity", example = "1", required = true)
    private Integer stuck;

    @JsonProperty(ApiNames.VERBLEIBENDE)
    @Schema(description = "Remaining Quantity", example = "1", required = true)
    private Integer verbleibende;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.BELADUNG)
    @Schema(description = "Wagon load", example = "holz")
    private String beladung;

    @JsonProperty(ApiNames.STATUS)
    @Schema(description = "Status", example = "GEKAUFT", required = true)
    private Status status;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/artikel/00001/abbildung.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.GROSSANSICHT)
    @Schema(description = "Large Image URL", example = "http://localhost:8086/ModellBahn/artikel/00001/grossansicht.jpg", accessMode = AccessMode.READ_ONLY)
    private String grossansicht;

    @SuppressExport
    @JsonProperty(ApiNames.ANDERUNGEN)
    @Schema(implementation = AnderungModel.class, description = "Modifications", accessMode = AccessMode.READ_ONLY)
    private List<AnderungModel> anderungen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(ArtikelModel other) {
        return new CompareToBuilder()
            .append(artikelId, other.artikelId)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(artikelId)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ArtikelModel)) {
            return false; 
        }

        ArtikelModel other = (ArtikelModel) obj;

        return new EqualsBuilder()
                .append(artikelId, other.artikelId)
                .isEquals();
    }
}
