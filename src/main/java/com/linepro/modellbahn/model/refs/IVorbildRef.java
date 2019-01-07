package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IVorbildRef extends IPictureRef, IRef {
    @JsonGetter(ApiNames.GATTUNG)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= INamedItemRef.class)
    @ApiModelProperty(value = "", required = true)
    IGattung getGattung();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Artikel description")
    String getBezeichnung();
}
