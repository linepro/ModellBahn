package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public interface IDecoderRef extends ILinkRef {

    @JsonGetter(ApiNames.DECODER_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The Decoder's id", accessMode = AccessMode.READ_ONLY, required = true)
    String getDecoderId();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The Decoder's description")
    String getBezeichnung();

    @JsonGetter(ApiNames.DECODER_TYP)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypRef.class)
    @ApiModelProperty(value = "The Decoder's type", required = true)
    IDecoderTyp getDecoderTyp();
}
