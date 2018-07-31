package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IDescribedEnum;

public class DescribedEnumWrapper {

    protected final IDescribedEnum dEnum;
    
    public DescribedEnumWrapper(IDescribedEnum dEnum) {
        this.dEnum = dEnum;
    }

    @JsonGetter(ApiNames.NAME)
    public String getName() {
        return dEnum.getName();
    }

    @JsonGetter(ApiNames.DESCRIPTION)
    public String getDescription() {
        return dEnum.getDescription();
    }
}
