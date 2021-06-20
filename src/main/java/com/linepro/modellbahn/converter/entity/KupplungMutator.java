package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component(PREFIX + "KupplungMutator")
public class KupplungMutator extends MutatorImpl<Kupplung, KupplungModel> {

    @Autowired
    public KupplungMutator(PathMutator pathMutator) {
        super(() -> new KupplungModel(), new NamedAbbildungTranscriber<>(pathMutator));
    }
}
