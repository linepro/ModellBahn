package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.ArtikelTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

@Component(PREFIX + "ArtikelMutator")
public class ArtikelMutator extends MutatorImpl<Artikel, ArtikelModel> {

    @Autowired
    public ArtikelMutator(AnderungMutator anderungMutator, ProduktMutator produktMutator, PathMutator pathMutator) {
        super(() -> new ArtikelModel(), new ArtikelTranscriber(anderungMutator, produktMutator, pathMutator));
    }

}
