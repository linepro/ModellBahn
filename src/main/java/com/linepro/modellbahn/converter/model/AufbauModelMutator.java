package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component(PREFIX + "AufbauModelMutator")
public class AufbauModelMutator extends MutatorImpl<AufbauModel, Aufbau> {

    public AufbauModelMutator() {
        super(() -> new Aufbau(), new NamedModelTranscriber<AufbauModel, Aufbau>());
    }
}
