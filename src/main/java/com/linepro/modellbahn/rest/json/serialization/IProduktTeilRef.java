package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IProduktTeilRef extends ILinkRef {

    @JsonGetter(ApiNames.TEIL)
    @ApiModelProperty(value = "")
    IProduktRef getTeil();

    @JsonGetter(ApiNames.ANZAHL)
    @ApiModelProperty(value = "")
    Integer getAnzahl();
}
