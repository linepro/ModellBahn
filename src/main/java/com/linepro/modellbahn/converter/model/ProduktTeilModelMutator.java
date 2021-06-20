package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.ProduktTeilModelTranscriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;

@Component(PREFIX + "ProduktTeilModelMutator")
public class ProduktTeilModelMutator extends MutatorImpl<ProduktTeilModel,ProduktTeil> {

    @Autowired
    public ProduktTeilModelMutator(ProduktLookup produktLookup) {
        super(() -> new ProduktTeil(), new ProduktTeilModelTranscriber(produktLookup));
    }
}
