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
import com.linepro.modellbahn.util.impexp.impl.FileNameImport;

import io.swagger.v3.oas.annotations.Hidden;
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
    ApiNames.LANGE, ApiNames.MASSSTAB, ApiNames.SPURWEITE, ApiNames.EPOCH, ApiNames.BAHNVERWALTUNG, ApiNames.VORBILD, ApiNames.GATTUNG, ApiNames.BETREIBSNUMMER,
    ApiNames.BAUZEIT, ApiNames.ACHSFOLG, ApiNames.SONDERMODELL, ApiNames.AUFBAU, ApiNames.LICHT, ApiNames.KUPPLUNG, ApiNames.STEUERUNG,
    ApiNames.DECODER_HERSTELLER, ApiNames.DECODER_BESTELL_NR, ApiNames.MOTOR_TYP, ApiNames.ANMERKUNG, ApiNames.ANLEITUNGEN,
    ApiNames.EXPLOSIONSZEICHNUNG, ApiNames.ABBILDUNG, ApiNames.GROSSANSICHT, ApiNames.DELETED })
@Schema(name = ApiNames.PRODUKT, description = "Product - template for article.")
public class ProduktRequest implements ItemRequest {

    private static final long serialVersionUID = -1251072878651679859L;

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

    @JsonProperty(ApiNames.BAHNVERWALTUNG_BEZEICHNUNG)
    @Schema(description = "Railway company", example = "DB")
    private String bahnverwaltungBezeichnung;

    @JsonProperty(ApiNames.VORBILD)
    @Schema(description = "Prototype", example = "BR89.0")
    private String vorbild;

    @JsonProperty(ApiNames.GATTUNG)
    @Schema(description = "Vehicle class", example = "BR89.0")
    private String gattung;

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

    @JsonProperty(ApiNames.ACHSFOLG_BEZEICHNUNG)
    @Schema(description = "Axle configuration", example = "CH2T")
    private String achsfolgBezeichnung;

    @JsonProperty(ApiNames.SONDERMODELL)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodell;

    @JsonProperty(ApiNames.SONDERMODELL_BEZEICHNUNG)
    @Schema(description = "Special model indicator", example = "MHI")
    private String sondermodellBezeichnung;

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

    @JsonProperty(ApiNames.DECODER_HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Decoder Manufacturer", example = "ESU")
    private String decoderTypHerstellerBezeichnung;

    @JsonProperty(ApiNames.DECODER_BESTELL_NR)
    @Schema(description = "Decoder Part Number", example = "62400")
    private String decoderTypBestellNr;

    @JsonProperty(ApiNames.MOTOR_TYP)
    @Schema(description = "Motor type", example = "5*")
    private String motorTyp;

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "Ex set")
    private String anmerkung;

    @Hidden
    @FileNameImport(keyFields = {ApiNames.HERSTELLER, ApiNames.BESTELL_NR})
    @JsonProperty(ApiNames.ANLEITUNGEN)
    private String anleitungen;

    @Hidden
    @FileNameImport(keyFields = {ApiNames.HERSTELLER, ApiNames.BESTELL_NR})
    @JsonProperty(ApiNames.EXPLOSIONSZEICHNUNG)
    private String explosionszeichnung;

    @Hidden
    @FileNameImport(keyFields = {ApiNames.HERSTELLER, ApiNames.BESTELL_NR})
    @JsonProperty(ApiNames.ABBILDUNG)
    private String abbildung;

    @Hidden
    @FileNameImport(keyFields = {ApiNames.HERSTELLER, ApiNames.BESTELL_NR})
    @JsonProperty(ApiNames.GROSSANSICHT)
    private String grossansicht;

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
