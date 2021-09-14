package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ArtikelRequestTranscriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.KupplungLookup;
import com.linepro.modellbahn.repository.lookup.LichtLookup;
import com.linepro.modellbahn.repository.lookup.MotorTypLookup;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;
import com.linepro.modellbahn.repository.lookup.SteuerungLookup;
import com.linepro.modellbahn.request.ArtikelRequest;

@Component(PREFIX + "ArtikelRequestMapper")
public class ArtikelRequestMapper extends MapperImpl<ArtikelRequest, Artikel> {

    @Autowired
    public ArtikelRequestMapper(ProduktLookup produktLookup, SteuerungLookup steuerungLookup, MotorTypLookup motorTypLookup,
                    LichtLookup lichtLookup, KupplungLookup kupplungLookup, DecoderLookup decoderLookup) {
        super(() -> new Artikel(), new ArtikelRequestTranscriber(produktLookup, steuerungLookup, motorTypLookup, lichtLookup,
                        kupplungLookup, decoderLookup));
    }

}
