package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.converter.request.AchsfolgRequestMapper;
import com.linepro.modellbahn.converter.request.AnderungRequestMapper;
import com.linepro.modellbahn.converter.request.AntriebRequestMapper;
import com.linepro.modellbahn.converter.request.ArtikelRequestMapper;
import com.linepro.modellbahn.converter.request.AufbauRequestMapper;
import com.linepro.modellbahn.converter.request.BahnverwaltungRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderCvRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderFunktionRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderTypCvRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderTypFunktionRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderTypRequestMapper;
import com.linepro.modellbahn.converter.request.EpochRequestMapper;
import com.linepro.modellbahn.converter.request.GattungRequestMapper;
import com.linepro.modellbahn.converter.request.HerstellerRequestMapper;
import com.linepro.modellbahn.converter.request.KategorieRequestMapper;
import com.linepro.modellbahn.converter.request.KupplungRequestMapper;
import com.linepro.modellbahn.converter.request.LichtRequestMapper;
import com.linepro.modellbahn.converter.request.MassstabRequestMapper;
import com.linepro.modellbahn.converter.request.MotorTypRequestMapper;
import com.linepro.modellbahn.converter.request.ProduktRequestMapper;
import com.linepro.modellbahn.converter.request.ProduktTeilRequestMapper;
import com.linepro.modellbahn.converter.request.ProtokollRequestMapper;
import com.linepro.modellbahn.converter.request.SondermodellRequestMapper;
import com.linepro.modellbahn.converter.request.SpurweiteRequestMapper;
import com.linepro.modellbahn.converter.request.SteuerungRequestMapper;
import com.linepro.modellbahn.converter.request.UnterKategorieRequestMapper;
import com.linepro.modellbahn.converter.request.VorbildRequestMapper;
import com.linepro.modellbahn.converter.request.ZugConsistRequestMapper;
import com.linepro.modellbahn.converter.request.ZugRequestMapper;
import com.linepro.modellbahn.converter.request.ZugTypRequestMapper;

@Import({
    AchsfolgRequestMapper.class, 
    AntriebRequestMapper.class, 
    ArtikelRequestMapper.class, 
    AnderungRequestMapper.class, 
    AufbauRequestMapper.class, 
    BahnverwaltungRequestMapper.class, 
    DecoderRequestMapper.class, 
    DecoderCvRequestMapper.class, 
    DecoderFunktionRequestMapper.class, 
    DecoderTypRequestMapper.class, 
    DecoderTypCvRequestMapper.class, 
    DecoderTypFunktionRequestMapper.class, 
    EpochRequestMapper.class, 
    GattungRequestMapper.class, 
    HerstellerRequestMapper.class, 
    KategorieRequestMapper.class, 
    UnterKategorieRequestMapper.class, 
    KupplungRequestMapper.class, 
    LichtRequestMapper.class, 
    MassstabRequestMapper.class, 
    MotorTypRequestMapper.class, 
    ProduktRequestMapper.class, 
    ProduktTeilRequestMapper.class, 
    ProtokollRequestMapper.class, 
    SondermodellRequestMapper.class, 
    SpurweiteRequestMapper.class, 
    SteuerungRequestMapper.class, 
    VorbildRequestMapper.class, 
    ZugRequestMapper.class, 
    ZugConsistRequestMapper.class, 
    ZugTypRequestMapper.class 
})
@Configuration(PREFIX + "ModelConverters")
public class ModelConverters {
}