package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.VorbildRequestTranscriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.lookup.AchsfolgLookup;
import com.linepro.modellbahn.repository.lookup.AntriebLookup;
import com.linepro.modellbahn.repository.lookup.BahnverwaltungLookup;
import com.linepro.modellbahn.repository.lookup.GattungLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.request.VorbildRequest;

@Component(PREFIX + "VorbildRequestMapper")
public class VorbildRequestMapper extends MapperImpl<VorbildRequest, Vorbild> {

    @Autowired
    public VorbildRequestMapper(GattungLookup gattungLookup, UnterKategorieLookup unterKategorieLookup, AntriebLookup antriebLookup,
                    BahnverwaltungLookup bahnverwaltungLookup, AchsfolgLookup achsfolgLookup) {
        super(() -> new Vorbild(), new VorbildRequestTranscriber(gattungLookup, unterKategorieLookup, antriebLookup, bahnverwaltungLookup, achsfolgLookup));
    }
}
