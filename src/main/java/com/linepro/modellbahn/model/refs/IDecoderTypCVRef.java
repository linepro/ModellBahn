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
    @ApiModelProperty(value = "CV number", example = "63", required = true)
    Integer getCv();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "CV usage", example = "Geräuschlautstärke", required = true)
    String getBezeichnung();

    @JsonGetter(ApiNames.WERKSEINSTELLUNG)
    @ApiModelProperty(value = "Default value", example = "63", required = true)
    Integer getWerkseinstellung();
}
