package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.hateoas.Hateoas.PagedSchema;
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
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.ARTIKEL_ID, ApiNames.HERSTELLER, ApiNames.HERSTELLER_BEZEICHNUNG, ApiNames.BESTELL_NR,
    ApiNames.BEZEICHNUNG, ApiNames.KATEGORIE, ApiNames.KATEGORIE_BEZEICHNUNG, ApiNames.UNTER_KATEGORIE,
    ApiNames.UNTER_KATEGORIE_BEZEICHNUNG, ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.MASSSTAB_BEZEICHNUNG, ApiNames.SPURWEITE
    , ApiNames.SPURWEITE_BEZEICHNUNG, ApiNames.EPOCH, ApiNames.EPOCH_BEZEICHNUNG, ApiNames.BAHNVERWALTUNG,
    ApiNames.BAHNVERWALTUNG_BEZEICHNUNG, ApiNames.VORBILD, ApiNames.GATTUNG, ApiNames.GATTUNG_BEZEICHNUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.ACHSFOLG, ApiNames.ACHSFOLG_BEZEICHNUNG, ApiNames.SONDERMODELL, ApiNames.SONDERMODELL_BEZEICHNUNG, ApiNames.AUFBAU, 
    ApiNames.AUFBAU_BEZEICHNUNG, ApiNames.LICHT, ApiNames.LICHT_BEZEICHNUNG, ApiNames.KUPPLUNG, ApiNames.KUPPLUNG_BEZEICHNUNG,
    ApiNames.STEUERUNG, ApiNames.STEUERUNG_BEZEICHNUNG, ApiNames.DECODER, ApiNames.DECODER_BEZEICHNUNG, ApiNames.MOTOR_TYP,
    ApiNames.MOTOR_TYP, ApiNames.MOTOR_TYP_BEZEICHNUNG, ApiNames.KAUFDATUM, ApiNames.WAHRUNG, ApiNames.PREIS, ApiNames.MENGE,
    ApiNames.VERBLEIBENDE, ApiNames.ANMERKUNG, ApiNames.BELADUNG, ApiNames.STATUS, ApiNames.ABBILDUNG, ApiNames.GROSSANSICHT,
    ApiNames.ANDERUNGEN, ApiNames.DECODEREN, ApiNames.FUNKTIONEN, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.ARTIKEL)
@Schema(name = ApiNames.ARTIKEL, description = "An article - may differ from product because of modificiations")
public class ArtikelModel extends SpringdocModel<ArtikelModel> implements ItemModel, Comparable<ArtikelModel> {

    private static final long serialVersionUID = 3146760791932382500L;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String artikelId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Manufacturer", example = "Marklin", accessMode = AccessMode.READ_ONLY)
    private String herstellerBezeichnung;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Part number", example = "3000", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorie;

    @JsonProperty(ApiNames.KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorieBezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorieBezeichnung;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String massstab;

    @JsonProperty(ApiNames.MASSSTAB_BEZEICHNUNG)
    @Schema(description = "Scale", example = "H0")
    private String massstabBezeichnung;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge", example = "H0", accessMode = AccessMode.READ_ONLY)
    private String spurweite;

    @JsonProperty(ApiNames.SPURWEITE_BEZEICHNUNG)
    @Schema(description = "Track gauge", example = "H0")
    private String spurweiteBezeichnung;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA", example = "IV", accessMode = AccessMode.READ_ONLY)
    private String epoch;

    @JsonProperty(ApiNames.EPOCH_BEZEICHNUNG)
    @Schema(description = "ERA", example = "IV")
    private String epochBezeichnung;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", example = "DB", accessMode = AccessMode.READ_ONLY)
    private String bahnverwaltung;

    @JsonProperty(ApiNames.BAHNVERWALTUNG_BEZEICHNUNG)
    @Schema(description = "Railway company", example = "DB")
    private String bahnverwaltungBezeichnung;

    @JsonProperty(ApiNames.VORBILD)
    @Schema(description = "Prototype", example = "BR89.0")
    private String vorbild;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0", accessMode = AccessMode.READ_ONLY)
    private String gattung;

    @JsonProperty(ApiNames.GATTUNG_BEZEICHNUNG)
    @Schema(description = "Vehicle class", example = "BR89.0")
    private String gattungBezeichnung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration", example = "CH2T", accessMode = AccessMode.READ_ONLY)
    private String achsfolg;

    @JsonProperty(ApiNames.ACHSFOLG_BEZEICHNUNG)
    @Schema(description = "Axle configuration", example = "CH2T")
    private String achsfolgBezeichnung;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI", accessMode = AccessMode.READ_ONLY)
    private String sondermodell;

    @JsonProperty(ApiNames.SONDERMODELL_BEZEICHNUNG)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodellBezeichnung;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", example = "LK", accessMode = AccessMode.READ_ONLY)
    private String aufbau;

    @JsonProperty(ApiNames.AUFBAU_BEZEICHNUNG)
    @Schema(description = "Construction", example = "LK")
    private String aufbauBezeichnung;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light Configuration", example = "L1V")
    private String licht;

    @JsonProperty(ApiNames.LICHT_BEZEICHNUNG)
    @Schema(description = "Light Configuration", example = "L1V")
    private String lichtBezeichnung;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplung;

    @JsonProperty(ApiNames.KUPPLUNG_BEZEICHNUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplungBezeichnung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerung;

    @JsonProperty(ApiNames.STEUERUNG_BEZEICHNUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerungBezeichnung;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

    @JsonProperty(ApiNames.MOTOR_TYP_BEZEICHNUNG)
    @Schema(description = "Motor type", example = "5*")
    private String motorTypBezeichnung;

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

    @JsonProperty(ApiNames.MENGE)
    @Schema(description = "Purchase Quantity", example = "1", required = true)
    private Integer menge;

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

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/anleitungen.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    @Schema(description = "Parts diagram URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/explosionszeichnung.pdf", accessMode = AccessMode.READ_ONLY)
    private String explosionszeichnung;

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

    @SuppressExport
    @JsonProperty(ApiNames.DECODEREN)
    @Schema(implementation = DecoderModel.class, description = "Decoders", accessMode = AccessMode.READ_ONLY)
    private List<DecoderModel> decoders;

    @SuppressExport
    @JsonProperty(ApiNames.FUNKTIONEN)
    @Schema(implementation = DecoderFunktionModel.class, description = "functions", accessMode = AccessMode.READ_ONLY)
    private List<DecoderFunktionModel> funktionen;

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

    @Schema(name = ApiNames.ARTIKEL + "Page")
    public static class PagedArtikelModel extends PagedSchema<ArtikelModel>{}
}
