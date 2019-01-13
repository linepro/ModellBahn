package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.FUNKTION,  ApiNames.BEZEICHNUNG, ApiNames.LINKS })
@ApiModel(value = ApiNames.FUNKTION, description = "Decoder function mapping.")
public interface IDecoderFunktionRef extends ILinkRef {

    @JsonGetter(ApiNames.FUNKTION)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IDecoderTypFunktionRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderTypFunktionRef", value = "Function", required = true)
    IDecoderTypFunktion getFunktion();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Usage", example = "Strinbeleuchtung", required = true)
    String getBezeichnung();
}
