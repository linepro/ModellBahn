package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface ILink {
    @JsonGetter(ApiNames.HREF)
    @ApiModelProperty(value = "The url for this link", required = true)
    String getHRef();

    @JsonGetter(ApiNames.REL)
    @ApiModelProperty(value = "The relationship for this link", required = true)
    String getRel();

    @JsonGetter(ApiNames.METHOD)
    @ApiModelProperty(value = "The method to be called (GET, DELETE, POST, PUT)", required = true)
    String getMethod();
}
