package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component
public class AufbauModelMutator extends MutatorImpl<AufbauModel, Aufbau> {

    public AufbauModelMutator() {
        super(() -> new Aufbau(), new NamedAbbildungTranscriber<AufbauModel, Aufbau>());
    }

}
