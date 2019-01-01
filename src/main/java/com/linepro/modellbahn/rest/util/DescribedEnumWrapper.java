package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IDescribedEnum;

import io.swagger.annotations.ApiModelProperty;

public class DescribedEnumWrapper {

    private final IDescribedEnum dEnum;
    
    public DescribedEnumWrapper(IDescribedEnum dEnum) {
        this.dEnum = dEnum;
    }

    @JsonGetter(ApiNames.NAMEN)
    @ApiModelProperty(value = "", required = true)
    public String getName() {
        return dEnum.getName();
    }

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "", required = true)
    public String getDescription() {
        return dEnum.getDescription();
    }
}
