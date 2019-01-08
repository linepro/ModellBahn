package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = ApiNames.MOTOR_TYP, description = "Type of motor - Märklin coding.")
public interface IMotorTypRef extends INamedItemRef {

    @JsonGetter(ApiNames.NAMEN)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Motor Type Code", example = "SFCM", required = true)
    String getName();

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Motor Type description", example = "Scheibenkollektor (klein)")
    String getBezeichnung();
}
