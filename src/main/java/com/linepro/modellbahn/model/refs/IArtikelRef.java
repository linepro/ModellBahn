package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IArtikelRef extends IPictureRef, IRef {

    @JsonGetter(ApiNames.ARTIKEL_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel id", required = true)
    String getArtikelId();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel description")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProduktRef.class)
    @ApiModelProperty(value = "Product details", required = true)
    IProdukt getProdukt();
}
