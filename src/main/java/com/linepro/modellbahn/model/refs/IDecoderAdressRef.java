package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.INDEX, ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.LINKS })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder address setting.")
public interface IDecoderAdressRef extends IAdress, ILinkRef {

    @JsonGetter(ApiNames.INDEX)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "0 based address index (always 0 for single address decoders)", example = "0", required = true)
    Integer getIndex();
}
