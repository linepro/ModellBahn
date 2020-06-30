package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.linepro.modellbahn.model.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.DescribedEnum;

@Service(PREFIX + "EnumsService")
public class EnumsService {

    public List<DescribedEnum> getEnumValues(DescribedEnum[] values) {
        return Stream.of(values).map(DescribedEnumModel::new)
                               .collect(Collectors.toList());
    }
}
