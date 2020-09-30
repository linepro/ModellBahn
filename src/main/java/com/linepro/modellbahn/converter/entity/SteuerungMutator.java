package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component(PREFIX + "SteuerungMutator")
public class SteuerungMutator extends MutatorImpl<Steuerung, SteuerungModel> {

    public SteuerungMutator() {
        super(() -> new SteuerungModel(), new NamedTranscriber<>());
    }

}
