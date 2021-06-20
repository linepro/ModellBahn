package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component(PREFIX + "SteuerungModelMutator")
public class SteuerungModelMutator extends MutatorImpl<SteuerungModel, Steuerung> {

    public SteuerungModelMutator() {
        super(() -> new Steuerung(), new NamedModelTranscriber<SteuerungModel, Steuerung>());
    }

}
