package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
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
 * IDecoderTyp.
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.PROTOKOLL,
        ApiNames.FAHRSTUFE, ApiNames.GERAUSCH, ApiNames.I_MAX, ApiNames.KONFIGURATION, ApiNames.DELETED,
        ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN})
@Schema(name = ApiNames.DECODER_TYP, description = "Decoder type - template for Decoder.")
public class DecoderTypModel extends RepresentationModel<DecoderTypModel> implements ItemModel {

    private static final long serialVersionUID = 8572025031906541322L;

    @JsonProperty(ApiNames.HERSTELLER)

    @Schema(implementation = HerstellerModel.class, name = "Manufacturer", required = true)
    private HerstellerModel hersteller;

    @JsonProperty(ApiNames.BESTELL_NR)

    @Schema(name = "Product numer", example = "62499", required = true)
    private String bestellNr;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @Schema(name = "Description", example = "LokSound M4")
    private String bezeichnung;

    @JsonProperty(ApiNames.I_MAX)

    @Schema(name = "Maximum current in mA", example = "1100")
    private BigDecimal iMax;

    @JsonProperty(ApiNames.PROTOKOLL)

    @Schema(implementation = ProtokollModel.class, name = "Default protocoll", required = true)
    private ProtokollModel protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)

    @Schema(name = "Default speed steps", example = "127", required = true)
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

    @JsonProperty(ApiNames.ANLEITUNGEN)

    @Schema(implementation = String.class, name = "Instructions URL", example = "http://localhost/Modelbahn/produkt/MARKLIN/3000/betrieb_3000.pdf", accessMode = AccessMode.READ_ONLY)
    private Path anleitungen;

    @JsonProperty(ApiNames.ADRESSEN)

    @Schema(implementation = DecoderTypAdressModel.class, name = "Assignable adresses", accessMode = AccessMode.READ_ONLY, required = true)
    private List<DecoderTypAdressModel> adressen;

    @JsonProperty(ApiNames.CVS)

    @Schema(implementation = DecoderTypCvModel.class, name = "Configurable CVs", accessMode = AccessMode.READ_ONLY)
    private List<DecoderTypCvModel> cvs;

    @JsonProperty(ApiNames.FUNKTIONEN)

    @Schema(implementation = DecoderTypFunktionModel.class, name = "Available function mappings", accessMode = AccessMode.READ_ONLY, required = true)
    private List<DecoderTypFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)

    @Schema(name = "True if soft deleted", example = "false", required = true)
    private Boolean deleted;
}
