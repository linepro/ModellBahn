package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    AchsfolgService.class,
    AntriebService.class,
    ArtikelService.class,
    AufbauService.class,
    BahnverwaltungService.class,
    DecoderCreatorImpl.class,
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
    NameGeneratorImpl.class,
    ProduktService.class,
    ProtokollService.class,
    SondermodellService.class,
    SpurweiteService.class,
    SteuerungService.class,
    VorbildService.class,
    ZugService.class,
    ZugTypService.class
})
@Configuration(PREFIX + "Services")
public class Services {
}
