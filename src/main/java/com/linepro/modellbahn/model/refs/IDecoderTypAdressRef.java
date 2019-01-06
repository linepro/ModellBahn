package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypAdressRef extends IAdress {

    @JsonGetter(ApiNames.INDEX)
    @ApiModelProperty(value = "", required = true)
    Integer getIndex();

    @JsonGetter(ApiNames.SPAN)
    @ApiModelProperty(value = "", required = true)
    Integer getSpan();
}
