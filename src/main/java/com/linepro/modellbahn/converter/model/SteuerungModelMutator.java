package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component("SteuerungModelMutator")
public class SteuerungModelMutator extends MutatorImpl<SteuerungModel, Steuerung> {

    public SteuerungModelMutator() {
        super(() -> new Steuerung(), new NamedTranscriber<SteuerungModel, Steuerung>());
    }

}
