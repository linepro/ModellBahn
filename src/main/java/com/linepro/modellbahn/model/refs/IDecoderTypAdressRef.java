package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypAdressRef extends IAdress, ILinkRef {

    @JsonGetter(ApiNames.INDEX)
    @ApiModelProperty(value = "The 0 based address index (always 0 for single address decoders)", required = true)
    Integer getIndex();

    @JsonGetter(ApiNames.SPAN)
    @ApiModelProperty(value = "The number of addresses consumed (1-16)", required = true)
    Integer getSpan();
}
