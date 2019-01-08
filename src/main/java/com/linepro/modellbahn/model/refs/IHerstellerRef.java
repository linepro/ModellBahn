package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = ApiNames.HERSTELLER, description = "Manufacturer.")
public interface IHerstellerRef extends INamedItemRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Hersteller coding", example = "MARKLIN", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Hersteller description", example = "Märklin")
    String getBezeichnung();
}
