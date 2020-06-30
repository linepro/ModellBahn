package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component(PREFIX + "SpurweiteModelMutator")
public class SpurweiteModelMutator extends MutatorImpl<SpurweiteModel, Spurweite> {

    public SpurweiteModelMutator() {
        super(() -> new Spurweite(), new NamedTranscriber<SpurweiteModel, Spurweite>());
    }

}
