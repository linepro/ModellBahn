package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IArtikelRef extends ILinkRef {

    @JsonGetter(ApiNames.NAMEN)
    @ApiModelProperty(value = "The Article's name", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "The Article's description")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.PRODUKT)
    @ApiModelProperty(value = "The Article's product", required = true)
    IProduktRef getProdukt();
    
    @JsonGetter(ApiNames.ABBILDUNG)
    @ApiModelProperty(value = "The Article's picture")
    String getAbbildung();
}
