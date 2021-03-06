package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;

@Component(PREFIX + "GattungMutator")
public class GattungMutator extends MutatorImpl<Gattung, GattungModel> {

    public GattungMutator() {
        super(() -> new GattungModel(), new NamedTranscriber<Gattung, GattungModel>());
    }

}
