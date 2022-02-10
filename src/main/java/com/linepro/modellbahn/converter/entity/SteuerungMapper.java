package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component(PREFIX + "SteuerungMapper")
public class SteuerungMapper extends MapperImpl<Steuerung, SteuerungModel> {

    @SuppressWarnings("unchecked")
    public SteuerungMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new SteuerungModel(), (Transcriber<Steuerung, SteuerungModel>) transcriber);
    }

}
