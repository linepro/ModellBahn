package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;

@Component(PREFIX + "GattungModelMutator")
public class GattungModelMutator extends MutatorImpl<GattungModel, Gattung> {

    public GattungModelMutator() {
        super(() -> new Gattung(), new NamedTranscriber<GattungModel, Gattung>());
    }

}
