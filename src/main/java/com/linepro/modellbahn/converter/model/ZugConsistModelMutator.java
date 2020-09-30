package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.ZugConsistModelTranscriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component(PREFIX + "ZugConsistModelMutator")
public class ZugConsistModelMutator extends MutatorImpl<ZugConsistModel, ZugConsist> {

    @Autowired
    public ZugConsistModelMutator(ZugRepository zugRepository, ItemLookup lookup, ArtikelLookup artikelLookup) {
        super(() -> new ZugConsist(), new ZugConsistModelTranscriber(zugRepository, lookup, artikelLookup));
    }
}
