package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ArtikelRequestTranscriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;
import com.linepro.modellbahn.request.ArtikelRequest;

@Component(PREFIX + "ArtikelRequestMapper")
public class ArtikelRequestMapper extends MapperImpl<ArtikelRequest, Artikel> {

    @Autowired
    public ArtikelRequestMapper(ProduktLookup produktLookup, SteuerungRepository steuerungRepository, MotorTypRepository motorTypRepository,
                    LichtRepository lichtRepository, KupplungRepository kupplungRepository, DecoderLookup decoderLookup, ItemLookup lookup) {
        super(() -> new Artikel(), new ArtikelRequestTranscriber(produktLookup, steuerungRepository, motorTypRepository, lichtRepository,
                        kupplungRepository, decoderLookup, lookup));
    }

}
