package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component(PREFIX + "AufbauModelMutator")
public class AufbauModelMutator extends MutatorImpl<AufbauModel, Aufbau> {

    @Autowired
    public AufbauModelMutator() {
        super(() -> new Aufbau(), new NamedTranscriber<AufbauModel, Aufbau>());
    }
}
