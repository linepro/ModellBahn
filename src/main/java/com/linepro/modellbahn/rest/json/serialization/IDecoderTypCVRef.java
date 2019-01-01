package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypCVRef extends ILinkRef {

    @JsonGetter(ApiNames.CV)
    @ApiModelProperty(value = "", required = true)
    Integer getCv();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @ApiModelProperty(value = "", required = true)
    Integer getWerkseinstellung();
}
