package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.linepro.modellbahn.configuration.SwaggerConfig;
import com.linepro.modellbahn.controller.AchsfolgController;
import com.linepro.modellbahn.controller.AntriebController;
import com.linepro.modellbahn.controller.ArtikelController;
import com.linepro.modellbahn.controller.AufbauController;
import com.linepro.modellbahn.controller.BahnverwaltungController;
import com.linepro.modellbahn.controller.DecoderController;
import com.linepro.modellbahn.controller.DecoderTypController;
import com.linepro.modellbahn.controller.EnumsController;
import com.linepro.modellbahn.controller.EpochController;
import com.linepro.modellbahn.controller.GattungController;
import com.linepro.modellbahn.controller.HerstellerController;
import com.linepro.modellbahn.controller.KategorieController;
import com.linepro.modellbahn.controller.KupplungController;
import com.linepro.modellbahn.controller.LandController;
import com.linepro.modellbahn.controller.LichtController;
import com.linepro.modellbahn.controller.MassstabController;
import com.linepro.modellbahn.controller.MotorTypController;
import com.linepro.modellbahn.controller.ProduktController;
import com.linepro.modellbahn.controller.ProtokollController;
import com.linepro.modellbahn.controller.SonderModellController;
import com.linepro.modellbahn.controller.SpurweiteController;
import com.linepro.modellbahn.controller.SteuerungController;
import com.linepro.modellbahn.controller.VorbildController;
import com.linepro.modellbahn.controller.WahrungController;
import com.linepro.modellbahn.controller.ZugController;
import com.linepro.modellbahn.controller.ZugTypController;
import com.linepro.modellbahn.converter.AchsfolgModelConverter;
import com.linepro.modellbahn.hateoas.NamedItemModelProcessor;
import com.linepro.modellbahn.logging.BusinessLogger;
import com.linepro.modellbahn.logging.LoggedAspect;
import com.linepro.modellbahn.logging.RequestLoggingConfiguration;
import com.linepro.modellbahn.security.AuthorizationServerConfig;
import com.linepro.modellbahn.security.CorsConfig;
import com.linepro.modellbahn.security.CustomAccessTokenConverter;
import com.linepro.modellbahn.security.OAuth2ResourceServerConfig;
import com.linepro.modellbahn.security.WebSecurityConfig;
import com.linepro.modellbahn.service.AchsfolgService;
import com.linepro.modellbahn.service.AntriebService;
import com.linepro.modellbahn.service.ArtikelService;
import com.linepro.modellbahn.service.AufbauService;
import com.linepro.modellbahn.service.BahnverwaltungService;
import com.linepro.modellbahn.service.DecoderService;
import com.linepro.modellbahn.service.DecoderTypService;
import com.linepro.modellbahn.service.EmailService;
import com.linepro.modellbahn.service.EnumsService;
import com.linepro.modellbahn.service.EpochService;
import com.linepro.modellbahn.service.GattungService;
import com.linepro.modellbahn.service.HerstellerService;
import com.linepro.modellbahn.service.KategorieService;
import com.linepro.modellbahn.service.KupplungService;
import com.linepro.modellbahn.service.LandService;
import com.linepro.modellbahn.service.LichtService;
import com.linepro.modellbahn.service.MassstabService;
import com.linepro.modellbahn.service.MotorTypService;
import com.linepro.modellbahn.service.ProduktService;
import com.linepro.modellbahn.service.ProtokollService;
import com.linepro.modellbahn.service.SonderModellService;
import com.linepro.modellbahn.service.SpurweiteService;
import com.linepro.modellbahn.service.SteuerungService;
import com.linepro.modellbahn.service.UserService;
import com.linepro.modellbahn.service.VorbildService;
import com.linepro.modellbahn.service.WahrungService;
import com.linepro.modellbahn.service.ZugService;
import com.linepro.modellbahn.service.ZugTypService;
import com.linepro.modellbahn.util.i18n.MessageTranslatorImpl;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@EnableJpaRepositories("com.linepro.modellbahn.persistence.repository") 
@EntityScan( basePackages = {"com.linepro.modellbahn.model.impl"} )
//@ComponentScan
@Import({
    // @Configuration
    SwaggerConfig.class,
    LoggedAspect.class,
    RequestLoggingConfiguration.class,
    AuthorizationServerConfig.class,
    CorsConfig.class,
    OAuth2ResourceServerConfig.class,
    WebSecurityConfig.class,

    // @Service
    AchsfolgService.class,
    AntriebService.class,
    ArtikelService.class,
    AufbauService.class,
    BahnverwaltungService.class,
    DecoderService.class,
    DecoderTypService.class,
    EmailService.class,
    EnumsService.class,
    EpochService.class,
    GattungService.class,
    HerstellerService.class,
    KategorieService.class,
    KupplungService.class,
    LandService.class,
    LichtService.class,
    MassstabService.class,
    MotorTypService.class,
    ProduktService.class,
    ProtokollService.class,
    SonderModellService.class,
    SpurweiteService.class,
    SteuerungService.class,
    UserService.class,
    VorbildService.class,
    WahrungService.class,
    ZugService.class,
    ZugTypService.class,
    
    // @RestController
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
    LandController.class,
    LichtController.class,
    MassstabController.class,
    MotorTypController.class,
    ProduktController.class,
    ProtokollController.class,
    SonderModellController.class,
    SpurweiteController.class,
    SteuerungController.class,
    VorbildController.class,
    WahrungController.class,
    ZugController.class,
    ZugTypController.class,
    
	// @Component
	AchsfolgModelConverter.class,
    NamedItemModelProcessor.class,
    BusinessLogger.class,
    CustomAccessTokenConverter.class,
    MessageTranslatorImpl.class,
})
@EnableWebMvc
@EnableAuthorizationServer
public class ModellbahnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModellbahnApplication.class, args);
	}
}

