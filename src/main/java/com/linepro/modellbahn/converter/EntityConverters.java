package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.converter.entity.AchsfolgMapper;
import com.linepro.modellbahn.converter.entity.AnderungMapper;
import com.linepro.modellbahn.converter.entity.AntriebMapper;
import com.linepro.modellbahn.converter.entity.ArtikelMapper;
import com.linepro.modellbahn.converter.entity.AufbauMapper;
import com.linepro.modellbahn.converter.entity.BahnverwaltungMapper;
import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypMapper;
import com.linepro.modellbahn.converter.entity.EpochMapper;
import com.linepro.modellbahn.converter.entity.GattungMapper;
import com.linepro.modellbahn.converter.entity.HerstellerMapper;
import com.linepro.modellbahn.converter.entity.KategorieMapper;
import com.linepro.modellbahn.converter.entity.KupplungMapper;
import com.linepro.modellbahn.converter.entity.LichtMapper;
import com.linepro.modellbahn.converter.entity.MassstabMapper;
import com.linepro.modellbahn.converter.entity.MotorTypMapper;
import com.linepro.modellbahn.converter.entity.ProduktDecoderTypMapper;
import com.linepro.modellbahn.converter.entity.ProduktMapper;
import com.linepro.modellbahn.converter.entity.ProduktTeilMapper;
import com.linepro.modellbahn.converter.entity.ProtokollMapper;
import com.linepro.modellbahn.converter.entity.SondermodellMapper;
import com.linepro.modellbahn.converter.entity.SpurweiteMapper;
import com.linepro.modellbahn.converter.entity.SteuerungMapper;
import com.linepro.modellbahn.converter.entity.UnterKategorieMapper;
import com.linepro.modellbahn.converter.entity.VorbildMapper;
import com.linepro.modellbahn.converter.entity.ZugConsistMapper;
import com.linepro.modellbahn.converter.entity.ZugMapper;
import com.linepro.modellbahn.converter.entity.ZugTypMapper;
import com.linepro.modellbahn.converter.entity.transcriber.AnderungTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ArtikelTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.BahnverwaltungTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderCvTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderFunktionTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypCvTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypFunktionTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.EpochTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.HerstellerTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.KategorieTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ProduktDecoderTypTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ProduktTeilTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ProduktTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.UnterKategorieTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.VorbildTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ZugConsistTranscriber;
import com.linepro.modellbahn.converter.entity.transcriber.ZugTranscriber;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;

@Import({
    AchsfolgMapper.class, 
    AnderungMapper.class, 
    AntriebMapper.class, 
    ArtikelMapper.class, 
    AufbauMapper.class, 
    BahnverwaltungMapper.class, 
    DecoderCvMapper.class, 
    DecoderFunktionMapper.class, 
    DecoderMapper.class, 
    DecoderTypCvMapper.class, 
    DecoderTypFunktionMapper.class, 
    DecoderTypMapper.class, 
    EpochMapper.class, 
    GattungMapper.class, 
    HerstellerMapper.class, 
    KategorieMapper.class, 
    KupplungMapper.class, 
    LichtMapper.class, 
    MassstabMapper.class, 
    MotorTypMapper.class, 
    ProduktMapper.class, 
    ProduktDecoderTypMapper.class,
    ProduktTeilMapper.class, 
    ProtokollMapper.class, 
    SondermodellMapper.class, 
    SpurweiteMapper.class, 
    SteuerungMapper.class, 
    UnterKategorieMapper.class, 
    VorbildMapper.class, 
    ZugConsistMapper.class, 
    ZugMapper.class, 
    ZugTypMapper.class,
    PathMapper.class,
    
    NamedTranscriber.class, 
    NamedAbbildungTranscriber.class, 
    AnderungTranscriber.class, 
    ArtikelTranscriber.class, 
    BahnverwaltungTranscriber.class, 
    DecoderCvTranscriber.class, 
    DecoderFunktionTranscriber.class, 
    DecoderTranscriber.class, 
    DecoderTypCvTranscriber.class, 
    DecoderTypFunktionTranscriber.class, 
    DecoderTypTranscriber.class, 
    EpochTranscriber.class, 
    HerstellerTranscriber.class, 
    KategorieTranscriber.class, 
    ProduktTranscriber.class, 
    ProduktDecoderTypTranscriber.class,
    ProduktTeilTranscriber.class, 
    UnterKategorieTranscriber.class, 
    VorbildTranscriber.class, 
    ZugConsistTranscriber.class, 
    ZugTranscriber.class
    })
@Configuration(PREFIX + "EntityConverters")
public class EntityConverters {
}