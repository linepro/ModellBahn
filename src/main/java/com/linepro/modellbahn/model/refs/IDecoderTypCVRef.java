package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypCVRef extends IRef {

    @JsonGetter(ApiNames.CV)
    @ApiModelProperty(value = "The CV number", required = true)
    Integer getCv();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "The CV usage", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @ApiModelProperty(value = "The default value", required = true)
    Integer getWerkseinstellung();
}
