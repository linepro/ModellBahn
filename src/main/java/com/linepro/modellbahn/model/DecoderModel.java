package com.linepro.modellbahn.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.DecoderStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * IDecoder.
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ApiNames.DECODER_ID, ApiNames.DECODER_TYP, ApiNames.BEZEICHNUNG, ApiNames.PROTOKOLL,
        ApiNames.FAHRSTUFE, ApiNames.DELETED, ApiNames.ADRESSEN, ApiNames.CVS, ApiNames.FUNKTIONEN})
@Schema(name = ApiNames.DECODER, description = "Decoder - installed or spare.")
public class DecoderModel extends RepresentationModel<DecoderModel> implements ItemModel {

    private static final long serialVersionUID = 831411120845485848L;

    @JsonProperty(ApiNames.DECODER_ID)

    @Schema(name = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY, required = true)
    private String decoderId;

    @JsonProperty(ApiNames.BEZEICHNUNG)

    @Schema(name = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.DECODER_TYP)

    @Schema(implementation = DecoderTypModel.class, name = "Decoder's type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.STATUS)

    @Schema(name = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.PROTOKOLL)

    @Schema(implementation = ProtokollModel.class, name = "Decoder protocol", required = true)
    private ProtokollModel protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)

    @Schema(name = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

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
}
