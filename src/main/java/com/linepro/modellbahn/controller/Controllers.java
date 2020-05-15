package com.linepro.modellbahn.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.linepro.modellbahn.controller.user.UserController;

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
    SonderModelController.class, 
    SpurweiteController.class, 
    SteuerungController.class, 
    VorbildController.class, 
    ZugController.class, 
    ZugTypController.class,
    UserController.class
})
@Configuration
public class Controllers {
}