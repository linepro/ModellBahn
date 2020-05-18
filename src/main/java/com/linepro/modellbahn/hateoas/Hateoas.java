package com.linepro.modellbahn.hateoas;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AchsfolgModelProcessor.class, 
    AnderungModelProcessor.class, 
    AntriebModelProcessor.class, 
    ArtikelModelProcessor.class, 
    AufbauModelProcessor.class, 
    BahnverwaltungModelProcessor.class, 
    DecoderAdressModelProcessor.class, 
    DecoderCvModelProcessor.class, 
    DecoderFunktionModelProcessor.class, 
    DecoderModelProcessor.class, 
    DecoderTypAdressModelProcessor.class, 
    DecoderTypCvModelProcessor.class, 
    DecoderTypFunktionModelProcessor.class, 
    DecoderTypModelProcessor.class, 
    EpochModelProcessor.class, 
    GattungModelProcessor.class, 
    HerstellerModelProcessor.class, 
    KategorieModelProcessor.class, 
    KupplungModelProcessor.class, 
    LichtModelProcessor.class, 
    MassstabModelProcessor.class, 
    MotorTypModelProcessor.class, 
    ProduktModelProcessor.class, 
    ProduktTeilModelProcessor.class, 
    ProtokollModelProcessor.class, 
    SondermodellModelProcessor.class, 
    SpurweiteModelProcessor.class, 
    SteuerungModelProcessor.class, 
    UnterKategorieModelProcessor.class, 
    VorbildModelProcessor.class, 
    ZugConsistModelProcessor.class, 
    ZugModelProcessor.class, 
    ZugTypModelProcessor.class
})
@Configuration
public class Hateoas {
}