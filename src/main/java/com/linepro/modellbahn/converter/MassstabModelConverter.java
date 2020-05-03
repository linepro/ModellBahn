package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component
public class MassstabModelConverter extends NamedConverter<MassstabModel,Massstab> {

    public MassstabModelConverter() {
        super(() -> new MassstabModel());
    }
}
