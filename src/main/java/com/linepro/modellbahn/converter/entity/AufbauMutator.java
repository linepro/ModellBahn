package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component
public class AufbauMutator extends MutatorImpl<Aufbau, AufbauModel> {

    public AufbauMutator() {
        super(() -> new AufbauModel(), new NamedAbbildungTranscriber<Aufbau, AufbauModel>());
    }

}
