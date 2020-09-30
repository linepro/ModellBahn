package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.converter.model.AchsfolgModelMutator;
import com.linepro.modellbahn.converter.model.AnderungModelMutator;
import com.linepro.modellbahn.converter.model.AntriebModelMutator;
import com.linepro.modellbahn.converter.model.ArtikelModelMutator;
import com.linepro.modellbahn.converter.model.AufbauModelMutator;
import com.linepro.modellbahn.converter.model.BahnverwaltungModelMutator;
import com.linepro.modellbahn.converter.model.DecoderAdressModelMutator;
import com.linepro.modellbahn.converter.model.DecoderCvModelMutator;
import com.linepro.modellbahn.converter.model.DecoderFunktionModelMutator;
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
import com.linepro.modellbahn.converter.model.ProduktTeilModelMutator;
import com.linepro.modellbahn.converter.model.ProtokollModelMutator;
import com.linepro.modellbahn.converter.model.SondermodellModelMutator;
import com.linepro.modellbahn.converter.model.SpurweiteModelMutator;
import com.linepro.modellbahn.converter.model.SteuerungModelMutator;
import com.linepro.modellbahn.converter.model.UnterKategorieModelMutator;
import com.linepro.modellbahn.converter.model.VorbildModelMutator;
import com.linepro.modellbahn.converter.model.ZugConsistModelMutator;
import com.linepro.modellbahn.converter.model.ZugModelMutator;
import com.linepro.modellbahn.converter.model.ZugTypModelMutator;

@Import({
    AchsfolgModelMutator.class, 
    AntriebModelMutator.class, 
    ArtikelModelMutator.class, 
    AnderungModelMutator.class, 
    AufbauModelMutator.class, 
    BahnverwaltungModelMutator.class, 
    DecoderModelMutator.class, 
    DecoderAdressModelMutator.class, 
    DecoderCvModelMutator.class, 
    DecoderFunktionModelMutator.class, 
    DecoderTypModelMutator.class, 
    DecoderTypAdressModelMutator.class, 
    DecoderTypCvModelMutator.class, 
    DecoderTypFunktionModelMutator.class, 
    EpochModelMutator.class, 
    GattungModelMutator.class, 
    HerstellerModelMutator.class, 
    KategorieModelMutator.class, 
    UnterKategorieModelMutator.class, 
    KupplungModelMutator.class, 
    LichtModelMutator.class, 
    MassstabModelMutator.class, 
    MotorTypModelMutator.class, 
    ProduktModelMutator.class, 
    ProduktTeilModelMutator.class, 
    ProtokollModelMutator.class, 
    SondermodellModelMutator.class, 
    SpurweiteModelMutator.class, 
    SteuerungModelMutator.class, 
    VorbildModelMutator.class, 
    ZugModelMutator.class, 
    ZugConsistModelMutator.class, 
    ZugTypModelMutator.class 
})
@Configuration(PREFIX + "ModelConverters")
public class ModelConverters {
}