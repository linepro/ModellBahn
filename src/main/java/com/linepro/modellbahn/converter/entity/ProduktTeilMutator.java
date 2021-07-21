package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ProduktTeilTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

@Component(PREFIX + "ProduktTeilMutator")
public class ProduktTeilMutator extends MapperImpl<ProduktTeil, ProduktTeilModel> {

    public ProduktTeilMutator() {
        super(() -> new ProduktTeilModel(), new ProduktTeilTranscriber());
    }
}
