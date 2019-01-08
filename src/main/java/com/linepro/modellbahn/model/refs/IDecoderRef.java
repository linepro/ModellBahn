package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@JsonPropertyOrder({ ApiNames.DECODER_ID, ApiNames.DECODER_TYP, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.DECODER, description = "Decoder - installed or spare.")
public interface IDecoderRef extends IRef {

    @JsonGetter(ApiNames.DECODER_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Decoder's id", example = "00001", accessMode = AccessMode.READ_ONLY, required = true)
    String getDecoderId();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Decoder's description", example = "ESU Loksound")
    String getBezeichnung();

    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderTypRef", value = "Decoder's type", required = true)
    IDecoderTyp getDecoderTyp();
}
