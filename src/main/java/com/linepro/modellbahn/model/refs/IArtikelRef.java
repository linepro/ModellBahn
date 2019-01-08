package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ApiNames.ARTIKEL_ID, ApiNames.BEZEICHNUNG, ApiNames.PRODUKT})
@ApiModel(value = ApiNames.ARTIKEL, description = "An article - may differ from product because of modificiations")
public interface IArtikelRef extends IPictureRef, IRef {

    @JsonGetter(ApiNames.ARTIKEL_ID)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel id", example = "00001", required = true)
    String getArtikelId();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel description", example = "BR 89.0 Dampftenderlokomotive")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.PRODUKT)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProduktRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IProduktRef", value = "Product details", required = true)
    IProdukt getProdukt();
}
