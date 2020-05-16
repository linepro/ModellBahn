package com.linepro.modellbahn.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.converter.model.AchsfolgModelMutator;
import com.linepro.modellbahn.converter.model.AnderungModelMutator;
import com.linepro.modellbahn.converter.model.AntriebModelMutator;
import com.linepro.modellbahn.converter.model.ArtikelModelMutator;
import com.linepro.modellbahn.converter.model.AufbauModelMutator;
import com.linepro.modellbahn.converter.model.BahnverwaltungModelMutator;
import com.linepro.modellbahn.converter.model.DecoderModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypAdressModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypCvModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypFunktionModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypModelMutator;
import com.linepro.modellbahn.converter.model.EpochModelMutator;
import com.linepro.modellbahn.converter.model.GattungModelMutator;
import com.linepro.modellbahn.converter.model.HerstellerModelMutator;
import com.linepro.modellbahn.converter.model.KategorieModelMutator;
import com.linepro.modellbahn.converter.model.KupplungModelMutator;
import com.linepro.modellbahn.converter.model.LichtModelMutator;
import com.linepro.modellbahn.converter.model.MassstabModelMutator;
import com.linepro.modellbahn.converter.model.MotorTypModelMutator;
import com.linepro.modellbahn.converter.model.ProduktModelMutator;
import com.linepro.modellbahn.converter.model.ProtokollModelMutator;
import com.linepro.modellbahn.converter.model.SonderModelModelMutator;
import com.linepro.modellbahn.converter.model.SpurweiteModelMutator;
import com.linepro.modellbahn.converter.model.SteuerungModelMutator;
import com.linepro.modellbahn.converter.model.UnterKategorieModelMutator;
import com.linepro.modellbahn.converter.model.VorbildModelMutator;
import com.linepro.modellbahn.converter.model.ZugModelMutator;
import com.linepro.modellbahn.converter.model.ZugTypModelMutator;

@Import({
    AchsfolgModelMutator.class, 
    AnderungModelMutator.class, 
    AntriebModelMutator.class, 
    ArtikelModelMutator.class, 
    AufbauModelMutator.class, 
    BahnverwaltungModelMutator.class, 
    DecoderModelMutator.class, 
    DecoderTypAdressModelMutator.class, 
    DecoderTypCvModelMutator.class, 
    DecoderTypFunktionModelMutator.class, 
    DecoderTypModelMutator.class, 
    EpochModelMutator.class, 
    GattungModelMutator.class, 
    HerstellerModelMutator.class, 
    KategorieModelMutator.class, 
    KupplungModelMutator.class, 
    LichtModelMutator.class, 
    MassstabModelMutator.class, 
    MotorTypModelMutator.class, 
    ProduktModelMutator.class, 
    ProtokollModelMutator.class, 
    SonderModelModelMutator.class, 
    SpurweiteModelMutator.class, 
    SteuerungModelMutator.class, 
    UnterKategorieModelMutator.class, 
    VorbildModelMutator.class, 
    ZugModelMutator.class, 
    ZugTypModelMutator.class 
})
@Configuration
public class ModelConverters {
}