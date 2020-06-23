package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.linepro.modellbahn.controller.impl.ApiNames;
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
 * DecoderModel
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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.DECODER_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.PROTOKOLL,
        ApiNames.FAHRSTUFE, ApiNames.GERAUSCH, ApiNames.I_MAX, ApiNames.KONFIGURATION, ApiNames.DELETED,
        ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN})
@Schema(name = ApiNames.DECODER, description = "Decoder - installed or spare.")
public class DecoderModel extends RepresentationModel<DecoderModel> implements ItemModel, Comparable<DecoderModel> {

    private static final long serialVersionUID = 831411120845485848L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(name = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY, required = true)
    private String decoderId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(name = "Product numer", example = "62499", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(name = "Maximum current in mA", example = "1100")
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(implementation = ProtokollModel.class, name = "Decoder protocol", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(name = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(name = "True if decoder supports sound", example = "true", required = true)
    private Boolean sound;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(name = "Configuration method", example = "CV", required = true)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(name = "Stecker", example = "NEM352")
    private Stecker stecker;

    @JsonProperty(ApiNames.STATUS)
    @Schema(name = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(implementation = String.class, name = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @JsonProperty(ApiNames.ADRESSEN)
    @Schema(implementation = DecoderAdressModel.class, name = "Decoder addresses", accessMode = AccessMode.READ_ONLY, required = true)
    private List<DecoderAdressModel> adressen;

    @JsonProperty(ApiNames.CVS)
    @Schema(implementation = DecoderCvModel.class, name = "Decoder cv values", accessMode = AccessMode.READ_ONLY)
    private List<DecoderCvModel> cvs;

    @JsonProperty(ApiNames.FUNKTIONEN)
    @Schema(implementation = DecoderFunktionModel.class, name = "Decoder functions", accessMode = AccessMode.READ_ONLY, required = true)
    private List<DecoderFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderModel other) {
        return new CompareToBuilder()
            .append(decoderId, other.decoderId)
            .toComparison();
    }

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

        if (!(obj instanceof DecoderModel)) {
            return false; 
        }

        DecoderModel other = (DecoderModel) obj;
        
        return new EqualsBuilder()
                .append(decoderId, other.decoderId)
                .isEquals();
    }
}
