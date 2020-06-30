package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component(PREFIX + "KupplungModelMutator")
public class KupplungModelMutator extends MutatorImpl<KupplungModel, Kupplung> {

    public KupplungModelMutator() {
        super(() -> new Kupplung(), new NamedTranscriber<KupplungModel, Kupplung>());
    }

}
