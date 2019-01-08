package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = ApiNames.MASSSTAB, description = "Scale - NEM 010.")
public interface IMassstabRef extends INamedItemRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Scale code", example = "H0", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Scale description", example = "H0 : 1:87 16,5mm")
    String getBezeichnung();
}
