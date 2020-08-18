package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component(PREFIX + "KategorieModelMutator")
public class KategorieModelMutator extends MutatorImpl<KategorieModel, Kategorie> {

    public KategorieModelMutator() {
        super(() -> new Kategorie(), new NamedModelTranscriber<KategorieModel, Kategorie>());
    }
}
