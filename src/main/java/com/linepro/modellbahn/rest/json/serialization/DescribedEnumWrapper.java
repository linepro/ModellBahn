package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.refs.IDescribedEnum;

public class DescribedEnumWrapper implements IDescribedEnum {

    private final IDescribedEnum enumValue;
    
    public DescribedEnumWrapper(IDescribedEnum enumValue) {
        this.enumValue = enumValue;
    }

    @Override
    public String getName() {
        return enumValue.getName();
    }

    @Override
    public String getBezeichnung() {
        return enumValue.getBezeichnung();
    }

    @Override
    public String getTooltip() {
        return enumValue.getTooltip();
    }
}