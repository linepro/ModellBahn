package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.VorbildModelTranscriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;

@Component(PREFIX + "VorbildModelMutator")
public class VorbildModelMutator extends MutatorImpl<VorbildModel, Vorbild> {

    @Autowired
    public VorbildModelMutator(GattungRepository gattungRepository, UnterKategorieLookup unterKategorieLookup, AntriebRepository antriebRepository,
                    BahnverwaltungRepository bahnverwaltungRepository, AchsfolgRepository achsfolgRepository, ItemLookup lookup) {
        super(() -> new Vorbild(), new VorbildModelTranscriber(gattungRepository, unterKategorieLookup, antriebRepository, bahnverwaltungRepository,
                        achsfolgRepository, lookup));
    }
}
