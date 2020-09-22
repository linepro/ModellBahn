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
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.model.enums.Konfiguration;
import com.linepro.modellbahn.model.enums.Stecker;
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
@JsonPropertyOrder({ApiNames.DECODER_ID, ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.I_MAX,
    ApiNames.PROTOKOLL, ApiNames.FAHRSTUFE, ApiNames.GERAUSCH, ApiNames.KONFIGURATION, ApiNames.STECKER, ApiNames.STATUS,
    ApiNames.ANLEITUNGEN, ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DECODER, itemRelation = ApiNames.DECODER)
@Schema(name = ApiNames.DECODER, description = "Decoder - installed or spare.")
public class DecoderModel extends RepresentationModel<DecoderModel> implements ItemModel, Comparable<DecoderModel> {

    private static final long serialVersionUID = 831411120845485848L;

    @JsonProperty(ApiNames.DECODER_ID)
    @Schema(description = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY)
    private String decoderId;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", example = "ESU", accessMode = AccessMode.READ_ONLY)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", accessMode = AccessMode.READ_ONLY)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Decoder's description", example = "ESU Loksound", accessMode = AccessMode.READ_ONLY)
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in A", example = "1.100", accessMode = AccessMode.READ_ONLY)
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Decoder protocol", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", accessMode = AccessMode.READ_ONLY)
    private Boolean sound;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", accessMode = AccessMode.READ_ONLY)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352", accessMode = AccessMode.READ_ONLY)
    private Stecker stecker;

    @JsonProperty(ApiNames.KAUFDATUM)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(implementation = LocalDate.class, name = "Purchase date", example = "1967-08-10")
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

    @SuppressExport
    @JsonProperty(ApiNames.ADRESSEN)
    @Schema(implementation = DecoderAdressModel.class, name = "Decoder addresses", accessMode = AccessMode.READ_ONLY)
    private List<DecoderAdressModel> adressen;

    @SuppressExport
    @JsonProperty(ApiNames.CVS)
    @Schema(implementation = DecoderCvModel.class, name = "Decoder cv values", accessMode = AccessMode.READ_ONLY)
    private List<DecoderCvModel> cvs;

    @SuppressExport
    @JsonProperty(ApiNames.FUNKTIONEN)
    @Schema(implementation = DecoderFunktionModel.class, name = "Decoder functions", accessMode = AccessMode.READ_ONLY)
    private List<DecoderFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
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
