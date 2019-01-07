package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypFunktionRef extends IRef {

    @JsonGetter(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    @ApiModelProperty(value = "", required = true)
    String getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.PROGRAMMABLE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "", required = true)
    Boolean getProgrammable();
}
