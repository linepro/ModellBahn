package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IWahrungRef extends INamedItemRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Currency code", example = "GBP", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Currency description", example = "Pound Serling")
    String getBezeichnung();

    @JsonGetter(ApiNames.DECIMALS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The number of decimal places", example = "2", required = true)
    Integer getDecimals();
}
