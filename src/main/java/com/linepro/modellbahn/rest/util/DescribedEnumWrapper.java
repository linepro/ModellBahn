package com.linepro.modellbahn.rest.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.linepro.modellbahn.model.IDescribedEnum;

public class DescribedEnumWrapper {

    private final IDescribedEnum dEnum;
    
    public DescribedEnumWrapper(IDescribedEnum dEnum) {
        this.dEnum = dEnum;
    }

    @JsonGetter(ApiNames.NAMEN)
    public String getName() {
        return dEnum.getName();
    }

    @JsonGetter(ApiNames.BEZEICHNUNG)
    public String getDescription() {
        return dEnum.getDescription();
    }
}
