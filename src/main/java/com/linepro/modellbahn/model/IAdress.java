package com.linepro.modellbahn.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = ApiNames.ADRESS, description = "A decoder address")
public interface IAdress {

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    @JsonGetter(ApiNames.ADRESS_TYP)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.util.AdressTyp", value = "Address type", required = true)
    AdressTyp getAdressTyp();

    /**
     * Gets the adress.
     *
     * @return the adress
     */
    @JsonGetter(ApiNames.ADRESS)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Digital address", required = true)
    Integer getAdress();
}
