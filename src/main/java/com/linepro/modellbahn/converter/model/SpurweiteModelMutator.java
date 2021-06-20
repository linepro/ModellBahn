package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component(PREFIX + "SpurweiteModelMutator")
public class SpurweiteModelMutator extends MutatorImpl<SpurweiteModel, Spurweite> {

    public SpurweiteModelMutator() {
        super(() -> new Spurweite(), new NamedModelTranscriber<SpurweiteModel, Spurweite>());
    }

}
