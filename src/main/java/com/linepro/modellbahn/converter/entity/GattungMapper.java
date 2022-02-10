package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;

@Component(PREFIX + "GattungMapper")
public class GattungMapper extends MapperImpl<Gattung, GattungModel> {

    @SuppressWarnings("unchecked")
    public GattungMapper(@Qualifier(PREFIX + "NamedTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new GattungModel(), (Transcriber<Gattung, GattungModel>) transcriber);
    }

}
