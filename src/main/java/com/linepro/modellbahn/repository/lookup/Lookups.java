package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import({
    AchsfolgLookup.class,
    AnderungLookup.class,
    AntriebLookup.class,
    ArtikelLookup.class,
    AufbauLookup.class,
    BahnverwaltungLookup.class,
    DecoderCvLookup.class,
    DecoderFunktionLookup.class,
    DecoderLookup.class,
    DecoderTypCvLookup.class,
    DecoderTypFunktionLookup.class,
    DecoderTypLookup.class,
    EpochLookup.class,
    GattungLookup.class,
    HerstellerLookup.class,
    KategorieLookup.class,
    KupplungLookup.class,
    LichtLookup.class,
    MassstabLookup.class,
    MotorTypLookup.class,
    ProduktLookup.class,
    ProduktTeilLookup.class,
    ProtokollLookup.class,
    SondermodellLookup.class,
    SpurweiteLookup.class,
    SteuerungLookup.class,
    UnterKategorieLookup.class,
    VorbildLookup.class,
    ZugConsistLookup.class,
    ZugLookup.class,
    ZugTypLookup.class
})
@Component(PREFIX + "Lookups")
public class Lookups {
}
