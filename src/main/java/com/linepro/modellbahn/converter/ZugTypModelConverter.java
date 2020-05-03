package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;

@Component
public class ZugTypModelConverter extends NamedConverter<ZugTypModel,ZugTyp> {

    public ZugTypModelConverter() {
        super(() -> new ZugTypModel());
    }
}
