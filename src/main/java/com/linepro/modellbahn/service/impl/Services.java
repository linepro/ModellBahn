package com.linepro.modellbahn.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AchsfolgService.class,
    AntriebService.class,
    ArtikelService.class,
    AufbauService.class,
    BahnverwaltungService.class,
    DecoderService.class,
    DecoderTypService.class,
    EnumsService.class,
    EpochService.class,
    GattungService.class,
    HerstellerService.class,
    KategorieService.class,
    KupplungService.class,
    LichtService.class,
    MassstabService.class,
    MotorTypService.class,
    ProduktService.class,
    ProtokollService.class,
    SonderModelService.class,
    SpurweiteService.class,
    SteuerungService.class,
    UserService.class,
    VorbildService.class,
    ZugService.class,
    ZugTypService.class
})
@Configuration
public class Services {
}
