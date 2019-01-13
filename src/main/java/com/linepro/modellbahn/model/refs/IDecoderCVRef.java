package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.CV, ApiNames.WERT, ApiNames.LINKS })
@ApiModel(value = ApiNames.CV, description = "Decoder CV setting.")
public interface IDecoderCVRef extends ILinkRef {

    @JsonGetter(ApiNames.CV)
    @JsonSerialize(as = IDecoderTypCVRef.class)
    @JsonView(Views.Public.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IDecoderTypCVRef", value = "Default address", example = "80")
    IDecoderTypCV getCv();

    @JsonGetter(ApiNames.WERT)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Assigned value", required = true)
    Integer getWert();
}
