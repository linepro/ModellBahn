package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component
public class AchsfolgModelConverter extends NamedConverter<AchsfolgModel,Achsfolg> {

    public AchsfolgModelConverter() {
        super(() -> new AchsfolgModel());
    }
}
