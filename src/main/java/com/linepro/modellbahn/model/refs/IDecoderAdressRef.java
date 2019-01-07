package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.model.IAdress;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface IDecoderAdressRef extends IAdress, IRef {

    @JsonGetter(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "The 0 based address index (always 0 for single address decoders)", required = true)
    Integer getIndex();
}
