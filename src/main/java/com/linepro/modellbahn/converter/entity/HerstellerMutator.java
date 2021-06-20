package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.HerstellerTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component(PREFIX + "HerstellerMutator")
public class HerstellerMutator extends MutatorImpl<Hersteller, HerstellerModel> {

    public HerstellerMutator(PathMutator pathMutator) {
        super(() -> new HerstellerModel(), new HerstellerTranscriber(pathMutator));
    }
}
