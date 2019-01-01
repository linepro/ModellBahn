package com.linepro.modellbahn.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IWahrung.
 * @author   $Author$
 * @version  $Id$
 */
@JsonRootName(value = ApiNames.WAHRUNG)
@JsonPropertyOrder({ApiNames.ID, ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.DECIMALS, ApiNames.DELETED, ApiNames.LINKS})
@ApiModel(value = ApiNames.WAHRUNG, description = "Currency.")
public interface IWahrung extends INamedItem<NameKey> {

    /**
     * Gets the decimals.
     *
     * @return the decimals
     */
    @JsonGetter(ApiNames.DECIMALS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "", required = true)
    Integer getDecimals();

    /**
     * Sets the decimals.
     *
     * @param decimals the new decimals
     */
    @JsonSetter(ApiNames.DECIMALS)
    void setDecimals(Integer decimals);

}