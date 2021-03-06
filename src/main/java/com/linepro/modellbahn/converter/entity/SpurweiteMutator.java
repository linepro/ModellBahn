package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component(PREFIX + "SpurweiteMutator")
public class SpurweiteMutator extends MutatorImpl<Spurweite, SpurweiteModel> {

    public SpurweiteMutator() {
        super(() -> new SpurweiteModel(), new NamedTranscriber<Spurweite, SpurweiteModel>());
    }

}
