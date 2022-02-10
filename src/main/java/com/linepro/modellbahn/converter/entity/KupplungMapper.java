package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component(PREFIX + "KupplungMapper")
public class KupplungMapper extends MapperImpl<Kupplung, KupplungModel> {

    @SuppressWarnings("unchecked")
    @Autowired
    public KupplungMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new KupplungModel(), (Transcriber<Kupplung, KupplungModel>) transcriber);
    }
}
