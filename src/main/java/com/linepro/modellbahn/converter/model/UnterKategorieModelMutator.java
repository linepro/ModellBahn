package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.UnterKategorieModelTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component(PREFIX + "UnterKategorieModelMutator")
public class UnterKategorieModelMutator extends MutatorImpl<UnterKategorieModel, UnterKategorie> {

    @Autowired
    public UnterKategorieModelMutator(KategorieRepository kategorieRepository, ItemLookup lookup) {
        super(() -> new UnterKategorie(), new UnterKategorieModelTranscriber(kategorieRepository, lookup));
    }

}
