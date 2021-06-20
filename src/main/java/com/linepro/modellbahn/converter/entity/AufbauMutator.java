package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component(PREFIX + "AufbauMutator")
public class AufbauMutator extends MutatorImpl<Aufbau, AufbauModel> {

    @Autowired
    public AufbauMutator(PathMutator pathMutator) {
        super(() -> new AufbauModel(), new NamedAbbildungTranscriber<>(pathMutator));
    }
}
