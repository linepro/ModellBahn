package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component(PREFIX + "LichtMapper")
public class LichtMapper extends MapperImpl<Licht, LichtModel> {

    @SuppressWarnings("unchecked")
    public LichtMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new LichtModel(), (Transcriber<Licht, LichtModel>) transcriber);
    }

}
