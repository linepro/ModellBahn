package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
 * DecoderTypModel
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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.I_MAX, ApiNames.PROTOKOLL,
        ApiNames.FAHRSTUFE, ApiNames.GERAUSCH, ApiNames.KONFIGURATION, ApiNames.STECKER, ApiNames.ANLEITUNGEN, ApiNames.ADRESSEN,
        ApiNames.CVS, ApiNames.FUNKTIONEN, ApiNames.DELETED, ApiNames.LINKS })
@Relation(collectionRelation = ApiNames.DECODER_TYP, itemRelation = ApiNames.DECODER_TYP)
@Schema(name = ApiNames.DECODER_TYP, description = "Decoder type - template for Decoder.")
public class DecoderTypModel extends RepresentationModel<DecoderTypModel> implements ItemModel, Comparable<DecoderTypModel> {

    private static final long serialVersionUID = 8572025031906541322L;

    @JsonProperty(ApiNames.HERSTELLER)
    @Schema(description = "Manufacturer", required = true)
    private String hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)
    @Schema(description = "Product numer", example = "62499", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(description = "Description", example = "LokSound M4")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)
    @Schema(description = "Maximum current in mA", example = "1100")
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)
    @Schema(description = "Default protocoll", required = true)
    private String protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @Schema(description = "Default speed steps", example = "127", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.GERAUSCH)
    @Schema(description = "True if decoder supports sound", example = "true", required = true)
    private Boolean sound;

    @JsonProperty(ApiNames.KONFIGURATION)
    @Schema(description = "Configuration method", example = "CV", required = true)
    private Konfiguration konfiguration;

    @JsonProperty(ApiNames.STECKER)
    @Schema(description = "Stecker", example = "NEM352")
    private Stecker stecker;

    @JsonProperty(ApiNames.ANLEITUNGEN)
    @Schema(description = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private String anleitungen;

    @SuppressExport
    @JsonProperty(ApiNames.ADRESSEN)
    @Schema(implementation = DecoderTypAdressModel.class, name = "Assignable adresses", accessMode = AccessMode.READ_ONLY)
    private List<DecoderTypAdressModel> adressen;

    @SuppressExport
    @JsonProperty(ApiNames.CVS)
    @Schema(implementation = DecoderTypCvModel.class, name = "Configurable CVs", accessMode = AccessMode.READ_ONLY)
    private List<DecoderTypCvModel> cvs;

    @SuppressExport
    @JsonProperty(ApiNames.FUNKTIONEN)
    @Schema(implementation = DecoderTypFunktionModel.class, name = "Available function mappings", accessMode = AccessMode.READ_ONLY)
    private List<DecoderTypFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)
    @Schema(description = "True if soft deleted", example = "false", accessMode = AccessMode.READ_ONLY)
    private Boolean deleted;

    @Override
    public int compareTo(DecoderTypModel other) {
        return new CompareToBuilder()
            .append(hersteller, other.hersteller)
            .append(bestellNr, other.bestellNr)
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

        if (!(obj instanceof DecoderTypModel)) {
            return false; 
        }

        DecoderTypModel other = (DecoderTypModel) obj;

        return new EqualsBuilder()
                .append(hersteller, other.hersteller)
                .append(bestellNr, other.bestellNr)
                .isEquals();
    }
}
