package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.converter.entity.AchsfolgMutator;
import com.linepro.modellbahn.converter.entity.AnderungMutator;
import com.linepro.modellbahn.converter.entity.AntriebMutator;
import com.linepro.modellbahn.converter.entity.ArtikelMutator;
import com.linepro.modellbahn.converter.entity.AufbauMutator;
import com.linepro.modellbahn.converter.entity.BahnverwaltungMutator;
import com.linepro.modellbahn.converter.entity.DecoderAdressMutator;
import com.linepro.modellbahn.converter.entity.DecoderCvMutator;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMutator;
import com.linepro.modellbahn.converter.entity.DecoderMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypAdressMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypCvMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypMutator;
import com.linepro.modellbahn.converter.entity.EpochMutator;
import com.linepro.modellbahn.converter.entity.GattungMutator;
import com.linepro.modellbahn.converter.entity.HerstellerMutator;
import com.linepro.modellbahn.converter.entity.KategorieMutator;
import com.linepro.modellbahn.converter.entity.KupplungMutator;
import com.linepro.modellbahn.converter.entity.LichtMutator;
import com.linepro.modellbahn.converter.entity.MassstabMutator;
import com.linepro.modellbahn.converter.entity.MotorTypMutator;
import com.linepro.modellbahn.converter.entity.ProduktMutator;
import com.linepro.modellbahn.converter.entity.ProduktTeilMutator;
import com.linepro.modellbahn.converter.entity.ProtokollMutator;
import com.linepro.modellbahn.converter.entity.SondermodellMutator;
import com.linepro.modellbahn.converter.entity.SpurweiteMutator;
import com.linepro.modellbahn.converter.entity.SteuerungMutator;
import com.linepro.modellbahn.converter.entity.UnterKategorieMutator;
import com.linepro.modellbahn.converter.entity.VorbildMutator;
import com.linepro.modellbahn.converter.entity.ZugConsistMutator;
import com.linepro.modellbahn.converter.entity.ZugMutator;
import com.linepro.modellbahn.converter.entity.ZugTypMutator;

@Import({
    AchsfolgMutator.class, 
    AnderungMutator.class, 
    AntriebMutator.class, 
    ArtikelMutator.class, 
    AufbauMutator.class, 
    BahnverwaltungMutator.class, 
    DecoderAdressMutator.class, 
    DecoderCvMutator.class, 
    DecoderFunktionMutator.class, 
    DecoderMutator.class, 
    DecoderTypAdressMutator.class, 
    DecoderTypCvMutator.class, 
    DecoderTypFunktionMutator.class, 
    DecoderTypMutator.class, 
    EpochMutator.class, 
    GattungMutator.class, 
    HerstellerMutator.class, 
    KategorieMutator.class, 
    KupplungMutator.class, 
    LichtMutator.class, 
    MassstabMutator.class, 
    MotorTypMutator.class, 
    ProduktMutator.class, 
    ProduktTeilMutator.class, 
    ProtokollMutator.class, 
    SondermodellMutator.class, 
    SpurweiteMutator.class, 
    SteuerungMutator.class, 
    UnterKategorieMutator.class, 
    VorbildMutator.class, 
    ZugConsistMutator.class, 
    ZugMutator.class, 
    ZugTypMutator.class,
    PathMutator.class
    })
@Configuration(PREFIX + "EntityConverters")
public class EntityConverters {
}