package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.ProduktTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;

@Component(PREFIX + "ProduktMutator")
public class ProduktMutator extends MutatorImpl<Produkt, ProduktModel> {

    @Autowired
    public ProduktMutator(UnterKategorieMutator unterKategorieMutator, ProduktTeilMutator teilMutator, PathMutator pathMutator) {
        super(() -> new ProduktModel(), new ProduktTranscriber(unterKategorieMutator, teilMutator, pathMutator));
    }
}
