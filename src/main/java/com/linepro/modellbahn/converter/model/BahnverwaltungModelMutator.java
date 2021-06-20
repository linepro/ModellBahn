package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.BahnverwaltungModelTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component(PREFIX + "BahnverwaltungModelMutator")
public class BahnverwaltungModelMutator extends MutatorImpl<BahnverwaltungModel, Bahnverwaltung> {

    public BahnverwaltungModelMutator() {
        super(() -> new Bahnverwaltung(), new BahnverwaltungModelTranscriber());
    }
}
