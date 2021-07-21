package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.VorbildRequestTranscriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.request.VorbildRequest;

@Component(PREFIX + "VorbildRequestMapper")
public class VorbildRequestMapper extends MapperImpl<VorbildRequest, Vorbild> {

    @Autowired
    public VorbildRequestMapper(GattungRepository gattungRepository, UnterKategorieLookup unterKategorieLookup, AntriebRepository antriebRepository,
                    BahnverwaltungRepository bahnverwaltungRepository, AchsfolgRepository achsfolgRepository, ItemLookup lookup) {
        super(() -> new Vorbild(), new VorbildRequestTranscriber(gattungRepository, unterKategorieLookup, antriebRepository, bahnverwaltungRepository,
                        achsfolgRepository, lookup));
    }
}
