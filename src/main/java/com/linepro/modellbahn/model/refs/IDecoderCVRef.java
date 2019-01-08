package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.CV, ApiNames.WERT })
@ApiModel(value = ApiNames.CV, description = "Decoder CV setting.")
public interface IDecoderCVRef extends IRef {

    @JsonGetter(ApiNames.CV)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "CV", required = true)
    Integer getCvValue();

    @JsonGetter(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "CV value", example = "80", required = true)
    Integer getWert();
}
