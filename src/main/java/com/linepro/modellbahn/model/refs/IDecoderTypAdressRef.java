package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.INDEX,  ApiNames.ADRESS_TYP, ApiNames.ADRESS, ApiNames.SPAN, ApiNames.LINKS })
@ApiModel(value = ApiNames.ADRESS, description = "Decoder type address - template for Decoder.")
public interface IDecoderTypAdressRef extends IAdress {

    @JsonGetter(ApiNames.INDEX)
    @ApiModelProperty(value = "0 based address index (always 0 for single address decoders)", example = "0", required = true)
    Integer getIndex();

    @JsonGetter(ApiNames.SPAN)
    @ApiModelProperty(value = "Number of addresses consumed (1-16)", example = "1", required = true)
    Integer getSpan();
}
