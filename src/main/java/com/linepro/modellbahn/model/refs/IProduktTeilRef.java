package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public interface IProduktTeilRef extends IRef {

    @JsonGetter(ApiNames.TEIL)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProduktRef.class)
    @ApiModelProperty(value = "", accessMode = AccessMode.READ_ONLY, required = true)
    IProdukt getTeil();

    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getAnzahl();
}
