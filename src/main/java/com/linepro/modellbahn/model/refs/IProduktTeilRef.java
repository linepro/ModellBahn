package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.rest.json.Views;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@JsonPropertyOrder({ ApiNames.TEIL, ApiNames.ANZAHL })
@ApiModel(value = ApiNames.TEIL, description = "Part of product (spares for rolling stock - contents for set &c).")
public interface IProduktTeilRef extends IRef {

    @JsonGetter(ApiNames.TEIL)
    @JsonView(Views.DropDown.class)
    @JsonSerialize(as= IProduktRef.class)
    @ApiModelProperty(dataType = "com.linepro.modellbahn.model.refs.IProduktRef", value = "Sub product details (spare parts / set contents)", accessMode = AccessMode.READ_ONLY, required = true)
    IProdukt getTeil();

    @JsonGetter(ApiNames.ANZAHL)
    @JsonView(Views.DropDown.class)
    @ApiModelProperty(value = "Number included", example = "1", required = true)
    Integer getAnzahl();
}
