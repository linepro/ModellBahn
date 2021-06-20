package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.AnderungModelTranscriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;

@Component(PREFIX + "AnderungModelMutator")
public class AnderungModelMutator extends MutatorImpl<AnderungModel, Anderung> {

    @Autowired
    public AnderungModelMutator(ArtikelLookup artikelLookup) {
        super(() -> new Anderung(), new AnderungModelTranscriber(artikelLookup));
    }
}
