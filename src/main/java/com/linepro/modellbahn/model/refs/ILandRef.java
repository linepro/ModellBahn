package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = ApiNames.LAND, description = "Country.")
public interface ILandRef extends INamedItemRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "ISO Country code", example = "UK", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Country Name", example = "Vereinigtes Königreich")
    String getBezeichnung();
}
