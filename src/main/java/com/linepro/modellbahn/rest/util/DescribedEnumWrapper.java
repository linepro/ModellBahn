package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.refs.IDescribedEnum;

import io.swagger.annotations.ApiModelProperty;

public class DescribedEnumWrapper {

    private final IDescribedEnum dEnum;
    
    public DescribedEnumWrapper(IDescribedEnum dEnum) {
        this.dEnum = dEnum;
    }

    @JsonGetter(ApiNames.NAMEN)
    @ApiModelProperty(value = "The enum value", required = true)
    public String getName() {
        return dEnum.getName();
    }

    @JsonGetter(ApiNames.BEZEICHNUNG)
    @ApiModelProperty(value = "The enum description", required = true)
    public String getDescription() {
        return dEnum.getBezeichnung();
    }
}
