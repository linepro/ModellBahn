package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IWahrungRef extends INamedItemRef {

    /**
     * Gets the decimals.
     *
     * @return the decimals
     */
    @JsonGetter(ApiNames.DECIMALS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getDecimals();

}
