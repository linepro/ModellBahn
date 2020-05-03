package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component
public class AntriebModelConverter extends NamedConverter<AntriebModel,Antrieb> {

    public AntriebModelConverter() {
        super(() -> new AntriebModel());
    }
}
