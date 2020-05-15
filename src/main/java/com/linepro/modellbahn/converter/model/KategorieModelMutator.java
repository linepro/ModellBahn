package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component
public class KategorieModelMutator extends MutatorImpl<KategorieModel, Kategorie> {

    public KategorieModelMutator() {
        super(() -> new Kategorie(), new NamedTranscriber<KategorieModel, Kategorie>());
    }

    @Override
    public Kategorie apply(KategorieModel source, Kategorie destination, int depth) {
        return super.apply(source, destination, depth);
    }

}
