package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.enums.DescribedEnum;

public class DescribedEnumModel implements DescribedEnum {

    private final DescribedEnum enumValue;
    
    public DescribedEnumModel(DescribedEnum enumValue) {
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