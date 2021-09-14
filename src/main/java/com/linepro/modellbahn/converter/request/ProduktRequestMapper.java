package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ProduktRequestTranscriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.lookup.AchsfolgLookup;
import com.linepro.modellbahn.repository.lookup.AufbauLookup;
import com.linepro.modellbahn.repository.lookup.BahnverwaltungLookup;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.EpochLookup;
import com.linepro.modellbahn.repository.lookup.GattungLookup;
import com.linepro.modellbahn.repository.lookup.HerstellerLookup;
import com.linepro.modellbahn.repository.lookup.KupplungLookup;
import com.linepro.modellbahn.repository.lookup.LichtLookup;
import com.linepro.modellbahn.repository.lookup.MassstabLookup;
import com.linepro.modellbahn.repository.lookup.MotorTypLookup;
import com.linepro.modellbahn.repository.lookup.SondermodellLookup;
import com.linepro.modellbahn.repository.lookup.SpurweiteLookup;
import com.linepro.modellbahn.repository.lookup.SteuerungLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.repository.lookup.VorbildLookup;
import com.linepro.modellbahn.request.ProduktRequest;

@Component(PREFIX + "ProduktRequestMapper")
public class ProduktRequestMapper extends MapperImpl<ProduktRequest, Produkt> {

    @Autowired
    public ProduktRequestMapper(HerstellerLookup herstellerLookup, UnterKategorieLookup unterKategorieLookup,
                    MassstabLookup massstabLookup, SpurweiteLookup spurweiteLookup, EpochLookup epochLookup,
                    BahnverwaltungLookup bahnverwaltungLookup, GattungLookup gattungLookup, AchsfolgLookup achsfolgLookup,
                    VorbildLookup vorbildLookup, SondermodellLookup sondermodellLookup, AufbauLookup aufbauLookup,
                    LichtLookup lichtLookup, KupplungLookup kupplungLookup, SteuerungLookup steuerungLookup,
                    DecoderTypLookup decoderTypLookup, MotorTypLookup motorTypLookup) {
        super(() -> new Produkt(), new ProduktRequestTranscriber(herstellerLookup, unterKategorieLookup, massstabLookup, spurweiteLookup,
                        epochLookup, bahnverwaltungLookup, gattungLookup, achsfolgLookup, vorbildLookup, sondermodellLookup,
                        aufbauLookup, lichtLookup, kupplungLookup, steuerungLookup, decoderTypLookup, motorTypLookup));
    }
}
