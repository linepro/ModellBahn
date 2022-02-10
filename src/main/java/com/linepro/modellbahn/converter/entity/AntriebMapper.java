package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component(PREFIX + "AntriebMapper")
public class AntriebMapper extends MapperImpl<Antrieb, AntriebModel> {

    @SuppressWarnings("unchecked")
    public AntriebMapper(@Qualifier(PREFIX  + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new AntriebModel(), (Transcriber<Antrieb, AntriebModel>) transcriber);
    }

}
