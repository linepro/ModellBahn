package com.linepro.modellbahn.model.refs;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IProduktRef extends IPictureRef, IRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IHerstellerRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IHerstellerRef", value = "The manufacturer", required = true)
    IHersteller getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The order number", example = "3000", required = true)
    String getBestellNr();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The description", example = "Dampftenderlok BR 89.0")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The length over puffers in cm.", example = "11.00")
    BigDecimal getLange();
}
