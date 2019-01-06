package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderFunktionRef extends ILinkRef {

    @JsonGetter(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    String getFunktionStr();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    String getBezeichnung();
}
