package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderAdressRef extends ILinkRef {

    @JsonGetter(ApiNames.ADRESS_TYP)
    @ApiModelProperty(value = "", required = true)
    AdressTyp getAdressTyp();

    @JsonGetter(ApiNames.ADRESS)
    @ApiModelProperty(value = "", required = true)
    String getAdress();
}
