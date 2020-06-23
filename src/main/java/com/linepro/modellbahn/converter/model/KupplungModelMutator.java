package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component
public class KupplungModelMutator extends MutatorImpl<KupplungModel, Kupplung> {

    public KupplungModelMutator() {
        super(() -> new Kupplung(), new NamedTranscriber<KupplungModel, Kupplung>());
    }

}
