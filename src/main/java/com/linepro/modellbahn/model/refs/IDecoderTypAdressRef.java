package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.INDEX,  ApiNames.ADRESS_TYP,  ApiNames.SPAN, ApiNames.ADRESS })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder type address - template for Decoder.")
public interface IDecoderTypAdressRef extends IAdress, IRef {

    @JsonGetter(ApiNames.INDEX)
    @ApiModelProperty(value = "The 0 based address index (always 0 for single address decoders)", example = "0", required = true)
    Integer getIndex();

    @JsonGetter(ApiNames.SPAN)
    @ApiModelProperty(value = "The number of addresses consumed (1-16)", example = "1", required = true)
    Integer getSpan();
}
