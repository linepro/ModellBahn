package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.REIHE,  ApiNames.FUNKTION, ApiNames.BEZEICHNUNG, ApiNames.PROGRAMMABLE})
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder type function mapping - template for Decoder.")
public interface IDecoderTypFunktionRef extends IRef {

    @JsonGetter(ApiNames.REIHE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Bank number (0-1) always 0 for single panel decoders", example = "0", required = true)
    Integer getReihe();

    @JsonGetter(ApiNames.FUNKTION)
    @ApiModelProperty(value = "Function number", example = "F0", required = true)
    String getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "Usage", example = "Strinbeleuchtung", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.PROGRAMMABLE)
    @JsonView(Views.Public.class)
    @ApiModelProperty(value = "True if this function can be reassigned", example = "false", required = true)
    Boolean getProgrammable();
}
