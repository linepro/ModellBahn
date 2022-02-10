package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component(PREFIX + "AchsfolgMapper")
public class AchsfolgMapper extends MapperImpl<Achsfolg, AchsfolgModel> {

    @SuppressWarnings("unchecked")
    public AchsfolgMapper(@Qualifier(PREFIX + "NamedTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new AchsfolgModel(), (Transcriber<Achsfolg, AchsfolgModel>) transcriber);
    }

}
