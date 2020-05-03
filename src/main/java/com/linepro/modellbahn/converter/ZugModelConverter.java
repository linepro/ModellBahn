package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

@Component
public class ZugModelConverter extends NamedConverter<ZugModel,Zug> {

    public ZugModelConverter() {
        super(() -> new ZugModel());
    }
}
