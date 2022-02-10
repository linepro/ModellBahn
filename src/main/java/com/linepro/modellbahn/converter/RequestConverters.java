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
import com.linepro.modellbahn.converter.request.transcriber.AnderungRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.ArtikelRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.BahnverwaltungRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderCvRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderFunktionRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypCvRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypFunktionRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.EpochRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.HerstellerRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.ProduktRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.ProduktTeilRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.UnterKategorieRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.VorbildRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.ZugConsistRequestTranscriber;
import com.linepro.modellbahn.converter.request.transcriber.ZugRequestTranscriber;

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
    ZugTypRequestMapper.class,
    
    NamedRequestTranscriber.class, 
    ArtikelRequestTranscriber.class, 
    AnderungRequestTranscriber.class, 
    BahnverwaltungRequestTranscriber.class, 
    DecoderRequestTranscriber.class, 
    DecoderCvRequestTranscriber.class, 
    DecoderFunktionRequestTranscriber.class, 
    DecoderTypRequestTranscriber.class, 
    DecoderTypCvRequestTranscriber.class, 
    DecoderTypFunktionRequestTranscriber.class, 
    EpochRequestTranscriber.class, 
    HerstellerRequestTranscriber.class, 
    UnterKategorieRequestTranscriber.class, 
    ProduktRequestTranscriber.class, 
    ProduktTeilRequestTranscriber.class, 
    VorbildRequestTranscriber.class, 
    ZugRequestTranscriber.class, 
    ZugConsistRequestTranscriber.class
})
@Configuration(PREFIX + "RequestConverters")
public class RequestConverters {
}