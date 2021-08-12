package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ProduktRequestTranscriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AufbauRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.EpochRepository;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.MassstabRepository;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.SondermodellRepository;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.request.ProduktRequest;

@Component(PREFIX + "ProduktRequestMapper")
public class ProduktRequestMapper extends MapperImpl<ProduktRequest, Produkt> {

    @Autowired
    public ProduktRequestMapper(HerstellerRepository herstellerRepository, UnterKategorieLookup unterKategorieLookup,
                    MassstabRepository massstabRepository, SpurweiteRepository spurweiteRepository, EpochRepository epochRepository,
                    BahnverwaltungRepository bahnverwaltungRepository, GattungRepository gattungRepository, AchsfolgRepository achsfolgRepository,
                    VorbildRepository vorbildRepository, SondermodellRepository sondermodellRepository, AufbauRepository aufbauRepository,
                    LichtRepository lichtRepository, KupplungRepository kupplungRepository, SteuerungRepository steuerungRepository,
                    DecoderTypLookup decoderTypLookup, MotorTypRepository motorTypRepository, ItemLookup lookup) {
        super(() -> new Produkt(), new ProduktRequestTranscriber(herstellerRepository, unterKategorieLookup, massstabRepository, spurweiteRepository,
                        epochRepository, bahnverwaltungRepository, gattungRepository, achsfolgRepository, vorbildRepository, sondermodellRepository,
                        aufbauRepository, lichtRepository, kupplungRepository, steuerungRepository, decoderTypLookup, motorTypRepository, lookup));
    }
}