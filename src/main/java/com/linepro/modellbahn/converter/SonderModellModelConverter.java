package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.SonderModell;
import com.linepro.modellbahn.model.SonderModellModel;

@Component
public class SonderModellModelConverter extends NamedConverter<SonderModellModel,SonderModell> {

    public SonderModellModelConverter() {
        super(() -> new SonderModellModel());
    }
}
