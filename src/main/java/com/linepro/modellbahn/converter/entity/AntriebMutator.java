package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component(PREFIX + "AntriebMutator")
public class AntriebMutator extends MutatorImpl<Antrieb, AntriebModel> {

    public AntriebMutator(PathMutator pathMutator) {
        super(() -> new AntriebModel(), new NamedAbbildungTranscriber<>(pathMutator));
    }

}
