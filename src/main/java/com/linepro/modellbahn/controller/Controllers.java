package com.linepro.modellbahn.controller;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AchsfolgController.class, 
    AntriebController.class, 
    ArtikelController.class, 
    AufbauController.class, 
    BahnverwaltungController.class, 
    DecoderController.class, 
    DecoderTypController.class, 
    EnumsController.class, 
    EpochController.class, 
    GattungController.class, 
    HerstellerController.class, 
    KategorieController.class, 
    KupplungController.class, 
    LichtController.class, 
    MassstabController.class, 
    MotorTypController.class, 
    ProduktController.class, 
    ProtokollController.class, 
    SondermodellController.class, 
    SpurweiteController.class, 
    SteuerungController.class, 
    VorbildController.class, 
    ZugController.class, 
    ZugTypController.class
})
@Configuration(PREFIX + "Controllers")
public class Controllers {
}