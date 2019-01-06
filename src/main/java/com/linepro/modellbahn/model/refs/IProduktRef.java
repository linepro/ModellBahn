package com.linepro.modellbahn.model.refs;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IProduktRef extends ILinkRef, IPictureRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "The manufacturer", required = true)
    IHersteller getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The order number", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The description")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The length over puffers in cm.")
    BigDecimal getLange();
}
