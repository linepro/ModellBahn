package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.i18n.MessageTranslator;
import com.linepro.modellbahn.model.DescribedEnumModel;
import com.linepro.modellbahn.model.enums.DescribedEnum;

import lombok.RequiredArgsConstructor;

@Service(PREFIX + "EnumsService")
@RequiredArgsConstructor
public class EnumsService {

    @Autowired
    private final MessageTranslator messageTranslator;

    public List<DescribedEnum> getEnumValues(DescribedEnum[] values) {
        return Stream.of(values)
                     .map(this::getModel)
                     .collect(Collectors.toList());
    }

    protected DescribedEnumModel getModel(DescribedEnum e) {
        return DescribedEnumModel.builder()
                        .name(e.getName())
                        .bezeichnung(messageTranslator.getMessage(e.getBezeichnung()))
                        .tooltip(messageTranslator.getMessage(e.getTooltip()))
                        .build();
    }
}
