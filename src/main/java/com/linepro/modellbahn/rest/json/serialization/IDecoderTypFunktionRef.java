package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypFunktionRef extends ILinkRef {

    @JsonGetter(ApiNames.REIHE)
    @ApiModelProperty(value = "", required = true)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    @ApiModelProperty(value = "", required = true)
    String getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.PROGRAMMABLE)
    @ApiModelProperty(value = "", required = true)
    Boolean getProgrammable();
}
