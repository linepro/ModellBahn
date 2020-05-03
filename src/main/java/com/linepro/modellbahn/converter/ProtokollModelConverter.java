package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component
public class ProtokollModelConverter extends NamedConverter<ProtokollModel,Protokoll> {

    public ProtokollModelConverter() {
        super(() -> new ProtokollModel());
    }
}
