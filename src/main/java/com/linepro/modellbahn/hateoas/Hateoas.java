package com.linepro.modellbahn.hateoas;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration.RenderSingleLinks;

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
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@Configuration(PREFIX + "Hateoas")
public class Hateoas {
    
    @Bean(PREFIX + "HalConfiguration")
    public HalConfiguration globalPolicy() {
      return new HalConfiguration() //
          .withRenderSingleLinks(RenderSingleLinks.AS_ARRAY); 
    }
}
