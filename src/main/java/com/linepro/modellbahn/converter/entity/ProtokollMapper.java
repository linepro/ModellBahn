package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component(PREFIX + "ProtokollMapper")
public class ProtokollMapper extends MapperImpl<Protokoll, ProtokollModel> {

    @SuppressWarnings("unchecked")
    public ProtokollMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new ProtokollModel(), (Transcriber<Protokoll, ProtokollModel>) transcriber);
    }

}
