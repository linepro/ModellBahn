package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component(PREFIX + "UnterKategorieModelMutator")
public class UnterKategorieModelMutator extends MutatorImpl<UnterKategorieModel, UnterKategorie> {

    public UnterKategorieModelMutator() {
        super(() -> new UnterKategorie(), new NamedTranscriber<UnterKategorieModel, UnterKategorie>());
    }

}
