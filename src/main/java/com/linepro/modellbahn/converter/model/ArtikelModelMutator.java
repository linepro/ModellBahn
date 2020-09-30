package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.ArtikelModelTranscriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;

@Component(PREFIX + "ArtikelModelMutator")
public class ArtikelModelMutator extends MutatorImpl<ArtikelModel, Artikel> {

    @Autowired
    public ArtikelModelMutator(ProduktLookup produktLookup, SteuerungRepository steuerungRepository, MotorTypRepository motorTypRepository,
                    LichtRepository lichtRepository, KupplungRepository kupplungRepository, DecoderLookup decoderLookup, ItemLookup lookup) {
        super(() -> new Artikel(), new ArtikelModelTranscriber(produktLookup, steuerungRepository, motorTypRepository, lichtRepository,
                        kupplungRepository, decoderLookup, lookup));
    }

}
