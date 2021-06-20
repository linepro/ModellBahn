package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.HerstellerModelTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component(PREFIX + "HerstellerModelMutator")
public class HerstellerModelMutator extends MutatorImpl<HerstellerModel, Hersteller> {

    public HerstellerModelMutator() {
        super(() -> new Hersteller(), new HerstellerModelTranscriber());
    }

}
