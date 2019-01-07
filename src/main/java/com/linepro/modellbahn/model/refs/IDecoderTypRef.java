package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.HERSTELLER, ApiNames.BESTELL_NR, ApiNames.BEZEICHNUNG })
@ApiModel(value = ApiNames.DECODER_TYP, description = "Decoder type - template for Decoder.")
public interface IDecoderTypRef extends IRef {

    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IHerstellerRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IHerstellerRef", value = "The manufacturer", required = true)
    IHersteller getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The product numer", example = "62499", required = true)
    String getBestellNr();
    
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "The description", example = "LokSound M4")
    String getBezeichnung();
}
