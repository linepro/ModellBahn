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
import com.linepro.modellbahn.converter.entity.DecoderAdressMapper;
import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypAdressMapper;
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

@Import({
    AchsfolgMapper.class, 
    AnderungMapper.class, 
    AntriebMapper.class, 
    ArtikelMapper.class, 
    AufbauMapper.class, 
    BahnverwaltungMapper.class, 
    DecoderAdressMapper.class, 
    DecoderCvMapper.class, 
    DecoderFunktionMapper.class, 
    DecoderMapper.class, 
    DecoderTypAdressMapper.class, 
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
    PathMapper.class
    })
@Configuration(PREFIX + "EntityConverters")
public class EntityConverters {
}