package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component(PREFIX + "SpurweiteMapper")
public class SpurweiteMapper extends MapperImpl<Spurweite, SpurweiteModel> {

    @SuppressWarnings("unchecked")
    public SpurweiteMapper(@Qualifier(PREFIX + "NamedTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new SpurweiteModel(), (Transcriber<Spurweite, SpurweiteModel>) transcriber);
    }

}
