package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderRef extends ILinkRef {

    @JsonGetter(ApiNames.DECODER)
    @ApiModelProperty(value = "", required = true)
    String getName();

    @JsonGetter(ApiNames.DECODER_TYP)
    @ApiModelProperty(value = "", required = true)
    IDecoderTypRef getDecoderTyp();
}
