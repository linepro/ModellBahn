package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component
public class SteuerungMutator extends MutatorImpl<Steuerung, SteuerungModel> {

    public SteuerungMutator() {
        super(() -> new SteuerungModel(), new NamedTranscriber<>());
    }

}
