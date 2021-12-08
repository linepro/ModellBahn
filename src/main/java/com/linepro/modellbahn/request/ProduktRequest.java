package com.linepro.modellbahn.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
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
 * ProduktRequest.
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
    ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.BAUZEIT, ApiNames.ACHSFOLG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG,
    ApiNames.DECODER_HERSTELLER, ApiNames.DECODER_BESTELL_NR, ApiNames.MOTOR_TYP, ApiNames.ANMERKUNG, ApiNames.DELETED })
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktRequest implements ItemRequest {
    /**
     * 
     */
    private static final long serialVersionUID = -1251072878651679859L;

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
    @Schema(description = "Category and subcategory", required = true)
    private String kategorie;

    @JsonProperty(ApiNames.UNTER_KATEGORIE)
    @Schema(description = "Category and subcategory", required = true)
    private String unterKategorie;

    @JsonProperty(ApiNames.LANGE)
    @Schema(description = "Length over puffers in cm.", example = "11.00")
    private BigDecimal lange;

    @JsonProperty(ApiNames.MASSSTAB)
    @Schema(description = "Scale")
    private String massstab;

    @JsonProperty(ApiNames.SPURWEITE)
    @Schema(description = "Track gauge")
    private String spurweite;

    @JsonProperty(ApiNames.EPOCH)
    @Schema(description = "ERA")
    private String epoch;

    @JsonProperty(ApiNames.BAHNVERWALTUNG)
    @Schema(description = "Railway company")
    private String bahnverwaltung;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class")
    private String gattung;

    @JsonProperty(ApiNames.BETREIBSNUMMER)
    @Schema(description = "Service number", example = "89 006")
    private String betreibsnummer;

    @JsonProperty(ApiNames.BAUZEIT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, description = "Construction date", example = "1934-01-01")
    private LocalDate bauzeit;

    @JsonProperty(ApiNames.ACHSFOLG)
    @Schema(description = "Axle configuration")
    private String achsfolg;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator")
    private String sondermodell;

    @JsonProperty(ApiNames.AUFBAU)
    @Schema(description = "Construction")
    private String aufbau;

    @JsonProperty(ApiNames.LICHT)
    @Schema(description = "Light configuration")
    private String licht;

    @JsonProperty(ApiNames.KUPPLUNG)
    @Schema(description = "Coupling configuration")
    private String kupplung;

    @JsonProperty(ApiNames.STEUERUNG)
    @Schema(description = "Control method")
    private String steuerung;

    @JsonProperty(ApiNames.DECODER_HERSTELLER)
    @Schema(description = "Decoder type")
    private String decoderTypHersteller;

    @JsonProperty(ApiNames.DECODER_BESTELL_NR)
    @Schema(description = "Decoder type")
    private String decoderTypBestellNr;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type")
    private String motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "Ex set")
    private String anmerkung;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

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

        if (!(obj instanceof ProduktRequest)) {
            return false; 
        }

        ProduktRequest other = (ProduktRequest) obj;

        return new EqualsBuilder()
                .append(bestellNr, other.bestellNr)
                .append(hersteller, other.hersteller)
                .isEquals();
    }
}
