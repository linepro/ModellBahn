package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component(PREFIX + "SteuerungMutator")
public class SteuerungMutator extends MutatorImpl<Steuerung, SteuerungModel> {

    public SteuerungMutator(PathMutator pathMutator) {
        super(() -> new SteuerungModel(), new NamedAbbildungTranscriber<>(pathMutator));
    }

}
