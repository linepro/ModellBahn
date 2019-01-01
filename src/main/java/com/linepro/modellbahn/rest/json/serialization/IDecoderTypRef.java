package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypRef extends ILinkRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @ApiModelProperty(value = "", required = true)
    String getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @ApiModelProperty(value = "", required = true)
    String getBestellNr();
    
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    String getBezeichnung();
}
