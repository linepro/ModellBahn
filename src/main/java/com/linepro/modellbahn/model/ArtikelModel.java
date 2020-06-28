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
    ApiNames.BELADUNG, ApiNames.STATUS, ApiNames.ANDERUNGEN, ApiNames.ABBILDUNG, ApiNames.DELETED})
@Relation(collectionRelation = ApiNames.ARTIKEL, itemRelation = ApiNames.ARTIKEL)
@Schema(name = ApiNames.ARTIKEL, description = "An article - may differ from product because of modificiations")
public class ArtikelModel extends RepresentationModel<ArtikelModel> implements ItemModel, Comparable<ArtikelModel> {

    private static final long serialVersionUID = 3146760791932382500L;

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

    @JsonProperty(ApiNames.KATEGORIE)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(name = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(name = "Scale", example = "H0")
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(name = "Track gauge", example = "H0")
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(name = "ERA", example = "IV")
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(name = "Railway company", example = "DB")
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(name = "Vehicle class", example = "BR89.0")
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(name = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(name = "Axle configuration", example = "CH2T")
    private String achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(name = "Special model indicator", example = "MHI")
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(name = "Construction")
    private String aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(name = "Light Configuration", example = "")
    private String licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(name = "Coupling configuration", example = "")
    private String kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(name = "Control method", example = "Digital")
    private String steuerung;

    @JsonProperty(ApiNames.DECODER)
    @Schema(name = "Decoder", example = "1")
    private String decoder;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(name = "Motor type", example = "5*")
    private String motorTyp;

    @JsonProperty(ApiNames.KAUFDATUM)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, name = "Purchase date", example = "1967-08-10")
    private LocalDate kaufdatum;

    @JsonProperty(ApiNames.WAHRUNG)
    @Schema(name = "Purchase currency, ISO 4217 code", example = "EUR")
    private String wahrung;

    @JsonProperty(ApiNames.PREIS)
    @Schema(name = "Purchase price", example = "115.95")
    private BigDecimal preis;

    @JsonProperty(ApiNames.STUCK)
    @Schema(name = "Purchase Quantity", example = "1", required = true)
    private Integer stuck;

    @JsonProperty(ApiNames.VERBLEIBENDE)
    @Schema(name = "Remaining Quantity", example = "1", required = true)
    private Integer verbleibende;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(name = "Remarks", example = "5* Motor and decoder")
    private String anmerkung;

    @JsonProperty(ApiNames.BELADUNG)
    @Schema(name = "Wagon load", example = "holz")
    private String beladung;

    @JsonProperty(ApiNames.STATUS)
    @Schema(name = "Status", example = "GEKAUFT", required = true)
    private Status status;

    @JsonProperty(ApiNames.ANDERUNGEN)
    @Schema(implementation = AnderungModel.class, name = "Modifications", accessMode = AccessMode.READ_ONLY)
    private List<AnderungModel> anderungen;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(name = "Image URL", example = "http://localhost:8086/ModellBahn/store/produkt/MARKLIN/3000/3000.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
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
