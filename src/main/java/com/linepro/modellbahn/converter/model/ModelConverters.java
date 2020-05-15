package com.linepro.modellbahn.converter.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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