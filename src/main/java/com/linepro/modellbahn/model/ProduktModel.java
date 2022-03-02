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
 * ProduktModel.
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.PRODUKT)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.HERSTELLER_BEZEICHNUNG, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG,
    ApiNames.KATEGORIE, ApiNames.KATEGORIE_BEZEICHNUNG, ApiNames.UNTER_KATEGORIE, ApiNames.UNTER_KATEGORIE_BEZEICHNUNG,
    ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.MASSSTAB_BEZEICHNUNG, ApiNames.SPURWEITE, ApiNames.SPURWEITE_BEZEICHNUNG,
    ApiNames.EPOCH, ApiNames.EPOCH_BEZEICHNUNG, ApiNames.BAHNVERWALTUNG, ApiNames.BAHNVERWALTUNG_BEZEICHNUNG, ApiNames.VORBILD,
    ApiNames.GATTUNG, ApiNames.GATTUNG_BEZEICHNUNG, ApiNames.BETREIBSNUMMER, ApiNames.BAUZEIT, ApiNames.ACHSFOLG, 
    ApiNames.ACHSFOLG_BEZEICHNUNG, ApiNames.SONDERMODELL, ApiNames.SONDERMODELL_BEZEICHNUNG, ApiNames.AUFBAU,
    ApiNames.AUFBAU_BEZEICHNUNG, ApiNames.LICHT, ApiNames.LICHT_BEZEICHNUNG, ApiNames.KUPPLUNG, ApiNames.KUPPLUNG_BEZEICHNUNG,
    ApiNames.STEUERUNG, ApiNames.STEUERUNG_BEZEICHNUNG, ApiNames.DECODER_HERSTELLER, ApiNames.DECODER_HERSTELLER_BEZEICHNUNG,
    ApiNames.DECODER_BESTELL_NR, ApiNames.MOTOR_TYP, ApiNames.MOTOR_TYP_BEZEICHNUNG, ApiNames.ANMERKUNG, ApiNames.ANLEITUNGEN,
    ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.GROSSANSICHT, ApiNames.DELETED, ApiNames.TEILEN, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.PRODUKT)
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktModel extends SpringdocModel<ProduktModel> implements ItemModel, Comparable<ProduktModel> {

    private static final long serialVersionUID = 2584784787206478907L;

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
    @Schema(description = "Description", example = "Dampftenderlok BR 89.0")
    private String bezeichnung;

    @JsonProperty(ApiNames.KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String kategorie;

    @SuppressExport
    @JsonProperty(ApiNames.KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String kategorieBezeichnung;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String unterKategorie;

    @SuppressExport
    @JsonProperty(ApiNames.UNTER_KATEGORIE_BEZEICHNUNG)
    @Schema(description = "Category and subcategory", accessMode = AccessMode.READ_ONLY)
    private String unterKategorieBezeichnung;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale", example = "H0")
    private String massstab;

    @SuppressExport
    @JsonProperty(ApiNames.MASSSTAB_BEZEICHNUNG)
    @Schema(description = "Scale", example = "H0")
    private String massstabBezeichnung;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge", example = "H0")
    private String spurweite;

    @SuppressExport
    @JsonProperty(ApiNames.SPURWEITE_BEZEICHNUNG)
    @Schema(description = "Track gauge", example = "H0")
    private String spurweiteBezeichnung;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA", example = "IV")
    private String epoch;

    @SuppressExport
    @JsonProperty(ApiNames.EPOCH_BEZEICHNUNG)
    @Schema(description = "ERA", example = "IV")
    private String epochBezeichnung;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", example = "DB")
    private String bahnverwaltung;

    @SuppressExport
    @JsonProperty(ApiNames.BAHNVERWALTUNG_BEZEICHNUNG)
    @Schema(description = "Railway company", example = "DB")
    private String bahnverwaltungBezeichnung;

    @JsonProperty(ApiNames.VORBILD)
    @Schema(description = "Prototype", example = "BR89.0")
    private String vorbild;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0")
    private String gattung;

    @SuppressExport
    @JsonProperty(ApiNames.GATTUNG_BEZEICHNUNG)
    @Schema(description = "Vehicle class", example = "BR89.0")
    private String gattungBezeichnung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.BAUZEIT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration", example = "CH2T")
    private String achsfolg;

    @SuppressExport
    @JsonProperty(ApiNames.ACHSFOLG_BEZEICHNUNG)
    @Schema(description = "Axle configuration", example = "CH2T")
    private String achsfolgBezeichnung;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodell;

    @SuppressExport
    @JsonProperty(ApiNames.SONDERMODELL_BEZEICHNUNG)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodellBezeichnung;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", example = "LK")
    private String aufbau;

    @SuppressExport
    @JsonProperty(ApiNames.AUFBAU_BEZEICHNUNG)
    @Schema(description = "Construction", example = "LK")
    private String aufbauBezeichnung;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light Configuration", example = "L1V")
    private String licht;

    @SuppressExport
    @JsonProperty(ApiNames.LICHT_BEZEICHNUNG)
    @Schema(description = "Light Configuration", example = "L1V")
    private String lichtBezeichnung;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplung;

    @SuppressExport
    @JsonProperty(ApiNames.KUPPLUNG_BEZEICHNUNG)
    @Schema(description = "Coupling configuration", example = "RELEX")
    private String kupplungBezeichnung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerung;

    @SuppressExport
    @JsonProperty(ApiNames.STEUERUNG_BEZEICHNUNG)
    @Schema(description = "Control method", example = "Digital")
    private String steuerungBezeichnung;

    @JsonProperty(ApiNames.DECODER_HERSTELLER)
    @Schema(description = "Decoder Manufacturer", example = "ESU")
    private String decoderTypHersteller;

    @SuppressExport
    @JsonProperty(ApiNames.DECODER_HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Decoder Manufacturer", example = "ESU")
    private String decoderTypHerstellerBezeichnung;

    @JsonProperty(ApiNames.DECODER_BESTELL_NR)
    @Schema(description = "Decoder Part Number", example = "62400")
    private String decoderTypBestellNr;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

    @SuppressExport
    @JsonProperty(ApiNames.MOTOR_TYP_BEZEICHNUNG)
    @Schema(description = "Motor type", example = "5*")
    private String motorTypBezeichnung;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "Ex set")
    private String anmerkung;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/anleitungen.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    @Schema(description = "Parts diagram URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/explosionszeichnung.pdf", accessMode = AccessMode.READ_ONLY)
    private String explosionszeichnung;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/produkt/MARKLIN/3000/abbildung.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.GROSSANSICHT)
    @Schema(description = "Large Image URL", example = "http://localhost:8086/ModellBahn/produkt/MARKLIN/3000/grossansicht.jpg", accessMode = AccessMode.READ_ONLY)
    private String grossansicht;

    @SuppressExport
    @JsonProperty(ApiNames.TEILEN)
    @Schema(implementation = ProduktTeilModel.class, description = "Product components", accessMode = AccessMode.READ_ONLY)
    private List<ProduktTeilModel> teilen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(ProduktModel other) {
        return new CompareToBuilder()
            .append(bestellNr, other.bestellNr)
            .append(hersteller, other.hersteller)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(hersteller)
            .append(bestellNr)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProduktModel)) {
            return false; 
        }

        ProduktModel other = (ProduktModel) obj;

        return new EqualsBuilder()
                .append(bestellNr, other.bestellNr)
                .append(hersteller, other.hersteller)
                .isEquals();
    }

    @Schema(name = ApiNames.PRODUKT + "Page")
    public static class PagedProduktModel extends PagedSchema<ProduktModel>{}
}
