package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderFunktionRef extends IRef {

    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypFunktionRef.class)
    @ApiModelProperty(value = "The function", required = true)
    IDecoderTypFunktion getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The usage", required = true)
    String getBezeichnung();
}
