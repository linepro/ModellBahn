package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypAdressRef {

    @JsonGetter(ApiNames.INDEX)
    @ApiModelProperty(value = "", required = true)
    Integer getIndex();

    @JsonGetter(ApiNames.ADRESS_TYP)
    @ApiModelProperty(value = "", required = true)
    AdressTyp getAdressTyp();

    @JsonGetter(ApiNames.SPAN)
    @ApiModelProperty(value = "", required = true)
    Integer getSpan();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @ApiModelProperty(value = "", required = true)
    Integer getWerkseinstellung();
}
