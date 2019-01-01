package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.rest.util.ApiNames;

import io.swagger.annotations.ApiModelProperty;

public interface ILink {
    @JsonGetter(ApiNames.HREF)
    @ApiModelProperty(value = "", required = true)
    String getHRef();

    @JsonGetter(ApiNames.REL)
    @ApiModelProperty(value = "", required = true)
    String getRel();

    @JsonGetter(ApiNames.METHOD)
    @ApiModelProperty(value = "", required = true)
    String getMethod();
}
