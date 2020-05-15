package com.linepro.modellbahn.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.linepro.modellbahn.model.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.DescribedEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnumsService {

    public List<DescribedEnum> getEnumValues(Class<? extends DescribedEnum> enumeration) {
        return getValues(enumeration).map(DescribedEnumModel::new)
                                                .collect(Collectors.toList());
    }

    private Stream<DescribedEnum> getValues(Class<? extends DescribedEnum> enumeration) {
        try {
            return Stream.of((DescribedEnum[]) enumeration.getDeclaredField("$VALUES").get(null));
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            log.error("Cannot get enum values for {}", enumeration.getCanonicalName());
        }
        
        return Stream.empty();
    }
}
