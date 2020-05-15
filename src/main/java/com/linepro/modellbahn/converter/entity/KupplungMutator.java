package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component
public class KupplungMutator extends MutatorImpl<Kupplung, KupplungModel> {

    public KupplungMutator() {
        super(() -> new KupplungModel(), new NamedAbbildungTranscriber<Kupplung, KupplungModel>());
    }

}
