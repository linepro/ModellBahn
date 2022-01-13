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
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.KATEGORIE, ApiNames.UNTER_KATEGORIE,
    ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.VORBILD, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.BAUZEIT, ApiNames.ACHSFOLG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG,
    ApiNames.DECODER_HERSTELLER, ApiNames.DECODER_BESTELL_NR, ApiNames.MOTOR_TYP, ApiNames.ANMERKUNG, ApiNames.ANLEITUNGEN,
    ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.TEILEN, ApiNames.ABBILDUNG, ApiNames.GROSSANSICHT, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DATA, itemRelation = ApiNames.PRODUKT)
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktModel extends SpringdocModel<ProduktModel> implements ItemModel, Comparable<ProduktModel> {
    private static final long serialVersionUID = 2584784787206478907L;

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
    @Schema(description = "Category", required = true)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Subcategory", required = true)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale", example = "H0")
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge", example = "H0")
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA", example = "IV")
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company", example = "DB")
    private String bahnverwaltung;

    @JsonProperty(ApiNames.VORBILD)
    @Schema(description = "Prototype", example = "BR89.0")
    private String vorbild;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0")
    private String gattung;

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

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction", example = "LK")
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

    @JsonProperty(ApiNames.DECODER_HERSTELLER)
    @Schema(description = "Decoder Manufacturer", example = "ESU")
    private String decoderTypHersteller;

    @JsonProperty(ApiNames.DECODER_BESTELL_NR)
    @Schema(description = "Decoder Part Number", example = "62400")
    private String decoderTypBestellNr;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "Ex set")
    private String anmerkung;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/anleitungen.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    @Schema(description = "Parts diagram URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/explosionszeichnung.pdf", accessMode = AccessMode.READ_ONLY)
    private String explosionszeichnung;

    @SuppressExport
    @JsonProperty(ApiNames.TEILEN)
    @Schema(implementation = ProduktTeilModel.class, description = "Product components", accessMode = AccessMode.READ_ONLY)
    private List<ProduktTeilModel> teilen;

    @JsonProperty(ApiNames.ABBILDUNG)
    @Schema(description = "Image URL", example = "http://localhost:8086/ModellBahn/produkt/MARKLIN/3000/abbildung.jpg", accessMode = AccessMode.READ_ONLY)
    private String abbildung;

    @JsonProperty(ApiNames.GROSSANSICHT)
    @Schema(description = "Large Image URL", example = "http://localhost:8086/ModellBahn/produkt/MARKLIN/3000/grossansicht.jpg", accessMode = AccessMode.READ_ONLY)
    private String grossansicht;

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
