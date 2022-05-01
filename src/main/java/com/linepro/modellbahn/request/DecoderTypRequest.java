package com.linepro.modellbahn.request;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.AdressTyp;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
import com.linepro.modellbahn.util.impexp.impl.FileNameImport;
import com.linepro.modellbahn.util.impexp.impl.SuppressExport;

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
 * DecoderTypRequest
 * @author $Author$
 * @version $Id$
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName(value = ApiNames.DECODER_TYP)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.I_MAX, ApiNames.PROTOKOLL,
        ApiNames.FAHRSTUFE, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.SPAN, ApiNames.GERAUSCH, ApiNames.KONFIGURATION,
        ApiNames.STECKER, ApiNames.ANLEITUNGEN, ApiNames.DELETED })
@Schema(name = ApiNames.DECODER_TYP, description = "Decoder type - template for Decoder.")
public class DecoderTypRequest implements ItemRequest {

    private static final long serialVersionUID = 6138762036136774557L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", required = true)
    private String hersteller;

    @SuppressExport
    @JsonProperty(ApiNames.HERSTELLER_BEZEICHNUNG)
    @Schema(description = "Manufacturer", required = true)
    private String herstellerBezeichnung;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "LokSound M4")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in A", example = "1.100")
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Default protocol", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Default speed steps", example = "127", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.ADRESS_TYP)
    @Schema(description = "Address type", required = true)
    private AdressTyp adressTyp;

    @JsonProperty(ApiNames.ADRESS)
    @Schema(description = "Default digital address", example = "80", required = true)
    private Integer adress;

    @JsonProperty(ApiNames.SPAN)
    @Schema(description = "Number of addresses consumed (1-16)", example = "1", required = true)
    private Integer span;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", required = true)
    private Boolean sound;

    @JsonProperty(ApiNames.MOTOR)
    @Schema(description = "True if decoder controls motor speed", example = "true", required = true)
    private Boolean motor;

    @JsonProperty(ApiNames.OUTPUTS)
    @Schema(description = "Number of function outputs", example = "2", required = true)
    private Integer outputs;

    @JsonProperty(ApiNames.SERVOS)
    @Schema(description = "Number of servo outputs", example = "2", required = true)
    private Integer servos;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", required = true)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352")
    private Stecker stecker;

    @Hidden
    @FileNameImport(keyFields = {ApiNames.HERSTELLER, ApiNames.BESTELL_NR})
    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

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

        if (!(obj instanceof DecoderTypRequest)) {
            return false; 
        }

        DecoderTypRequest other = (DecoderTypRequest) obj;

        return new EqualsBuilder()
                .append(hersteller, other.hersteller)
                .append(bestellNr, other.bestellNr)
                .isEquals();
    }
}
