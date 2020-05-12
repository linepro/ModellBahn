package com.linepro.modellbahn.model;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.rest.json.Views;

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
    @JsonView(Views.DropDown.class)
    @Schema(name = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY, required = true)
    private String decoderId;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @Schema(name = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = DecoderTypModel.class, name = "Decoder's type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.STATUS)
    @JsonView(Views.Public.class)
    @Schema(name = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.PROTOKOLL)
    @JsonView(Views.DropDown.class)
    @Schema(implementation = ProtokollModel.class, name = "Decoder protocol", required = true)
    private ProtokollModel protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @JsonView(Views.Public.class)
    @Schema(name = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.ADRESSEN)
    @JsonView(Views.Public.class)
    @Schema(implementation = DecoderAdressModel.class, name = "Decoder addresses", accessMode = AccessMode.READ_ONLY, required = true)
    private Set<DecoderAdressModel> adressen;

    @JsonProperty(ApiNames.CVS)
    @JsonView(Views.Public.class)
    @Schema(implementation = DecoderCvModel.class, name = "Decoder cv values", accessMode = AccessMode.READ_ONLY)
    private Set<DecoderCvModel> cvs;

    @JsonProperty(ApiNames.FUNKTIONEN)
    @JsonView(Views.Public.class)
    @Schema(implementation = DecoderFunktionModel.class, name = "Decoder functions", accessMode = AccessMode.READ_ONLY, required = true)
    private Set<DecoderFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @Schema(name = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
