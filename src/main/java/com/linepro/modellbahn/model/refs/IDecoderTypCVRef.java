package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.CV,  ApiNames.BEZEICHNUNG, ApiNames.WERKSEINSTELLUNG })
@ApiModel(value = ApiNames.CV, description = "Decoder type CV - template for Decoder.")
public interface IDecoderTypCVRef extends IRef {

    @JsonGetter(ApiNames.CV)
    @ApiModelProperty(value = "The CV number", example = "63", required = true)
    Integer getCv();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "The CV usage", example = "Geräuschlautstärke", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @ApiModelProperty(value = "The default value", example = "63", required = true)
    Integer getWerkseinstellung();
}
