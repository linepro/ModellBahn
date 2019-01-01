package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderCVRef extends ILinkRef {

    @JsonGetter(ApiNames.CV)
    @ApiModelProperty(value = "", required = true)
    Integer getCv();

    @JsonGetter(ApiNames.WERT)
    @ApiModelProperty(value = "", required = true)
    Integer getWert();
}
