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
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DecoderRequest
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.DECODER)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.ARTIKEL_ID, ApiNames.BEZEICHNUNG,
                     ApiNames.PROTOKOLL, ApiNames.FAHRSTUFE, ApiNames.ADRESS, ApiNames.KAUFDATUM, ApiNames.WAHRUNG, ApiNames.PREIS,
                     ApiNames.ANMERKUNG, ApiNames.STATUS, ApiNames.DELETED })
@Schema(name = ApiNames.DECODER, description = "Decoder - installed or spare.")
public class DecoderRequest implements ItemRequest {

    private static final long serialVersionUID = 5179582335912818583L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "ESU", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Manufacturer", example = "ESU", accessMode = AccessMode.READ_ONLY)
    private String herstellerBezeichnung;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.ARTIKEL_ID)
    @Schema(description = "Artikel id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String artikelId;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in A", example = "1.100", accessMode = AccessMode.READ_ONLY)
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Decoder protocol", example = "MFX", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.PROTOKOLL_BEZEICHNUNG)
    @Schema(description = "Decoder protocol", example = "MFX", required = true)
    private String protokollBezeichnung;

    @JsonProperty(ApiNames.ADRESS_TYP)
    @Schema(description = "Address type", accessMode = AccessMode.READ_ONLY)
    private AdressTyp adressTyp;

    @JsonProperty(ApiNames.ADRESS)
    @Schema(description = "Digital address", example = "28", required = true)
    private Integer adress;

    @JsonProperty(ApiNames.SPAN)
    @Schema(description = "Number of addresses consumed (1-16)", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer span;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", accessMode = AccessMode.READ_ONLY)
    private Boolean sound;

    @JsonProperty(ApiNames.MOTOR)
    @Schema(description = "True if decoder controls motor speed", example = "true", accessMode = AccessMode.READ_ONLY)
    private Boolean motor;

    @JsonProperty(ApiNames.OUTPUTS)
    @Schema(description = "Number of function outputs", example = "2", accessMode = AccessMode.READ_ONLY)
    private Integer outputs;

    @JsonProperty(ApiNames.SERVOS)
    @Schema(description = "Number of servo outputs", example = "2", accessMode = AccessMode.READ_ONLY)
    private Integer servos;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", accessMode = AccessMode.READ_ONLY)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352", accessMode = AccessMode.READ_ONLY)
    private Stecker stecker;

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

    @JsonProperty(ApiNames.ANMERKUNG)
    @Schema(description = "Remarks", example = "ESU Project nnnn")
    private String anmerkung;

    @JsonProperty(ApiNames.STATUS)
    @Schema(description = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/decoderTyp/ESU/62400/anleitungen.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(decoderId)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DecoderRequest)) {
            return false; 
        }

        DecoderRequest other = (DecoderRequest) obj;

        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .isEquals();
    }
}
