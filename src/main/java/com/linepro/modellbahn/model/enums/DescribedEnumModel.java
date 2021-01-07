package com.linepro.modellbahn.model.enums;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.linepro.modellbahn.model.SpringdocModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class DescribedEnumModel extends SpringdocModel<DescribedEnumModel> implements Comparable<DescribedEnumModel> {

    private String name;

    private String bezeichnung;

    private String tooltip;

    @Override
    public int compareTo(DescribedEnumModel other) {
        return new CompareToBuilder()
            .append(name, other.name)
            .toComparison();
    }
}
