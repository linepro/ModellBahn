package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ProduktTeilTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

@Component(PREFIX + "ProduktTeilMutator")
public class ProduktTeilMutator extends MutatorImpl<ProduktTeil, ProduktTeilModel> {

    public ProduktTeilMutator() {
        super(() -> new ProduktTeilModel(), new ProduktTeilTranscriber());
    }
}
