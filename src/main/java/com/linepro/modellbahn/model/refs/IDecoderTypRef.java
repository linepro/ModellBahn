package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderTypRef extends ILinkRef {

    /**
     * Gets the hersteller.
     *
     * @return the hersteller
     */
    @JsonGetter(ApiNames.HERSTELLER)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "", required = true)
    IHersteller getHersteller();

    @JsonGetter(ApiNames.BESTELL_NR)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    String getBestellNr();
    
    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "")
    String getBezeichnung();
}
