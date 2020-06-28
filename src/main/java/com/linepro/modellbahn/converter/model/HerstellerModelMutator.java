package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component("HerstellerModelMutator")
public class HerstellerModelMutator extends MutatorImpl<HerstellerModel, Hersteller> {

    public HerstellerModelMutator() {
        super(() -> new Hersteller(), new NamedTranscriber<HerstellerModel, Hersteller>());
    }

}
