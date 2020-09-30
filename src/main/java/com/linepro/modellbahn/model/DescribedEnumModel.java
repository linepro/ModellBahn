package com.linepro.modellbahn.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.hateoas.RepresentationModel;

import com.linepro.modellbahn.model.enums.DescribedEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DescribedEnumModel extends RepresentationModel<DescribedEnumModel> implements DescribedEnum, Comparable<DescribedEnumModel> {

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
