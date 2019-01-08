package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ ApiNames.NAMEN, ApiNames.BEZEICHNUNG, ApiNames.ABBILDUNG })
@ApiModel(value = ApiNames.KUPPLUNG, description = "Coupling configuration - Märklin coding.")
public interface IKupplungRef extends INamedItemRef, IPictureRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Kupplung code", example = "RELEX", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Kupplung description", example = "Relex")
    String getBezeichnung();
}
