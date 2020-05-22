package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.model.enums.DescribedEnum;

public class DescribedEnumModel extends RepresentationModel<DescribedEnumModel> implements DescribedEnum, Comparable<DescribedEnumModel> {

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

    @Override
    public int compareTo(DescribedEnumModel other) {
        return new CompareToBuilder()
            .append(enumValue, other.enumValue)
            .toComparison();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(enumValue)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DescribedEnumModel)) {
            return false; 
        }

        DescribedEnumModel other = (DescribedEnumModel) obj;
        
        return new EqualsBuilder()
                .append(enumValue, other.enumValue)
                .isEquals();
    }
}