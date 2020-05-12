package com.linepro.modellbahn.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.linepro.modellbahn.model.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.DescribedEnum;

import lombok.SneakyThrows;

@Service
public class EnumsService extends AbstractService {
    @SneakyThrows
    public List<DescribedEnum> getEnumValues(Class<? extends DescribedEnum> enumeration) {
        return Stream.of((DescribedEnum[])enumeration.getDeclaredField("$VALUES").get(null)).map(DescribedEnumModel::new).collect(Collectors.toList());
    }
}
