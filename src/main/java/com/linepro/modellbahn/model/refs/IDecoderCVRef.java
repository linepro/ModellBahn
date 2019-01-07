package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderCVRef extends IRef {

    @JsonGetter(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The CV number", required = true)
    Integer getCvValue();

    @JsonGetter(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The value", required = true)
    Integer getWert();
}
