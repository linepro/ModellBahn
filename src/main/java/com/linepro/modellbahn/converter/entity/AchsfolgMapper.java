package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component(PREFIX + "AchsfolgMapper")
public class AchsfolgMapper extends MapperImpl<Achsfolg, AchsfolgModel> {

    public AchsfolgMapper() {
        super(() -> new AchsfolgModel(), new NamedTranscriber<Achsfolg, AchsfolgModel>());
    }

}
