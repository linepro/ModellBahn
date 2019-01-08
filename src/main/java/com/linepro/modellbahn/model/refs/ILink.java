package com.linepro.modellbahn.model.refs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface ILink {
    @JsonGetter(ApiNames.HREF)
    @ApiModelProperty(value = "Url for this link", example = "http://localhost/ModellBahn/api/example", required = true)
    String getHRef();

    @JsonGetter(ApiNames.REL)
    @ApiModelProperty(value = "Relationship for this link", example = "self", required = true)
    String getRel();

    @JsonGetter(ApiNames.METHOD)
    @ApiModelProperty(value = "Method to be called (GET, DELETE, POST, PUT)", example = "GET", required = true)
    String getMethod();
}
