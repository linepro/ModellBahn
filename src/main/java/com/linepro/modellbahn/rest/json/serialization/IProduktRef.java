package com.linepro.modellbahn.rest.json.serialization;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IProduktRef extends ILinkRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @ApiModelProperty(value = "", required = true)
    String getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @ApiModelProperty(value = "", required = true)
    String getBestellNr();
    
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.LANGE)
    @ApiModelProperty(value = "")
    BigDecimal getLange();
    
    @JsonGetter(ApiNames.ABBILDUNG)
    @ApiModelProperty(value = "")
    String getAbbildung();
}
