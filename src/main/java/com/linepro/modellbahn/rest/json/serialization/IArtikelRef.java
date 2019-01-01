package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IArtikelRef extends ILinkRef {

    @JsonGetter(ApiNames.NAMEN)
    @ApiModelProperty(value = "", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.PRODUKT)
    @ApiModelProperty(value = "", required = true)
    IProduktRef getProdukt();
    
    @JsonGetter(ApiNames.ABBILDUNG)
    @ApiModelProperty(value = "")
    String getAbbildung();
}
