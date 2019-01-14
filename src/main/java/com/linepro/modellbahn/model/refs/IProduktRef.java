package com.linepro.modellbahn.model.refs;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG, ApiNames.LANGE })
@ApiModel(value = ApiNames.PRODUKT, description = "Product - template for article.")
public interface IProduktRef extends IPictureRef, IRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IHerstellerRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IHerstellerRef", value = "Manufacturer", required = true)
    IHersteller getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Part number", example = "3000", required = true)
    String getBestellNr();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Description", example = "Dampftenderlok BR 89.0")
    String getBezeichnung();
    
    @JsonGetter(ApiNames.UNTER_KATEGORIE)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IUnterKategorie.class)
    IUnterKategorie getUnterKategorie();

    @JsonGetter(ApiNames.LANGE)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Length over puffers in cm.", example = "11.00")
    BigDecimal getLange();
}
