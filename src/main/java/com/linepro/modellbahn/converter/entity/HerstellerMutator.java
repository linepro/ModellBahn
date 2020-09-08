package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.HerstellerTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component(PREFIX + "HerstellerMutator")
public class HerstellerMutator extends MutatorImpl<Hersteller, HerstellerModel> {

    public HerstellerMutator() {
        super(() -> new HerstellerModel(), new HerstellerTranscriber());
    }
}
