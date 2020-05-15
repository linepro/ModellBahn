package com.linepro.modellbahn.converter.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
    SonderModelMutator.class, 
    SpurweiteMutator.class, 
    SteuerungMutator.class, 
    UnterKategorieMutator.class, 
    VorbildMutator.class, 
    ZugConsistMutator.class, 
    ZugMutator.class, 
    ZugTypMutator.class 
    })
@Configuration
public class EntityConverters {
}