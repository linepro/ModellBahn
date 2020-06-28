package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component("BahnverwaltungModelMutator")
public class BahnverwaltungModelMutator extends MutatorImpl<BahnverwaltungModel, Bahnverwaltung> {

    public BahnverwaltungModelMutator() {
        super(() -> new Bahnverwaltung(), new NamedTranscriber<BahnverwaltungModel, Bahnverwaltung>());
    }

}
