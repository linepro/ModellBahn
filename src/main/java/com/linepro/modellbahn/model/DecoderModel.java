package com.linepro.modellbahn.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.model.base.ItemModelImpl;
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.rest.json.Views;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = ApiNames.DECODER, description = "Decoder - installed or spare.")
public class DecoderModel extends ItemModelImpl<DecoderModel> {

    private static final long serialVersionUID = 831411120845485848L;

    @JsonProperty(ApiNames.DECODER_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Decoder's id", example = "00001", access = "READ_ONLY", required = true)
    private String decoderId;

    @JsonProperty(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Decoder's description", example = "ESU Loksound")
    private String bezeichnung;

    @JsonProperty(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.DecoderTypModel", value = "Decoder's type", required = true)
    private DecoderTypModel decoderTyp;

    @JsonProperty(ApiNames.STATUS)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Decoder status", example = "INSTALIERT", required = true)
    private DecoderStatus status;

    @JsonProperty(ApiNames.PROTOKOLL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.ProtokollModel", value = "Decoder protocol", required = true)
    private ProtokollModel protokoll;

    @JsonProperty(ApiNames.FAHRSTUFE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "Decoder speed steps", example = "27", required = true)
    private Integer fahrstufe;

    @JsonProperty(ApiNames.ADRESSEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.IDecoderAdressRef;", value = "Decoder addresses", access = "READ_ONLY", required = true)
    private Set<DecoderAdressModel> adressen;

    @JsonProperty(ApiNames.CVS)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.IDecoderCvRef;", value = "Decoder cv values", access = "READ_ONLY")
    private Set<DecoderCvModel> cvs;

    @JsonProperty(ApiNames.FUNKTIONEN)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "[Lcom.linepro.modellbahn.model.IDecoderFunktionRef;", value = "Decoder functions", access = "READ_ONLY", required = true)
    private Set<DecoderFunktionModel> funktionen;

    @JsonProperty(ApiNames.DELETED)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if soft deleted", example = "false", required = true)
    protected Boolean deleted;
}
