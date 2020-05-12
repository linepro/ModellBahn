package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import com.linepro.modellbahn.configuration.MvcConfig;
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
import com.linepro.modellbahn.controller.LichtController;
import com.linepro.modellbahn.controller.MassstabController;
import com.linepro.modellbahn.controller.MotorTypController;
import com.linepro.modellbahn.controller.ProduktController;
import com.linepro.modellbahn.controller.ProtokollController;
import com.linepro.modellbahn.controller.SonderModellController;
import com.linepro.modellbahn.controller.SpurweiteController;
import com.linepro.modellbahn.controller.SteuerungController;
import com.linepro.modellbahn.controller.VorbildController;
import com.linepro.modellbahn.controller.ZugController;
import com.linepro.modellbahn.controller.ZugTypController;
import com.linepro.modellbahn.controller.impl.FileServiceImpl;
import com.linepro.modellbahn.controller.impl.FileUploadHandlerImpl;
import com.linepro.modellbahn.controller.user.UserController;
import com.linepro.modellbahn.hateoas.AchsfolgModelProcessor;
import com.linepro.modellbahn.i18n.MessageTranslatorImpl;
import com.linepro.modellbahn.i18n.RequestLocaleFilter;
import com.linepro.modellbahn.logging.BusinessLogger;
import com.linepro.modellbahn.logging.LoggedAspect;
import com.linepro.modellbahn.logging.RequestLoggingConfiguration;
import com.linepro.modellbahn.security.AuthorizationServerConfig;
import com.linepro.modellbahn.security.CorsConfig;
import com.linepro.modellbahn.security.CustomAccessTokenConverter;
import com.linepro.modellbahn.security.OAuth2ResourceServerConfig;
import com.linepro.modellbahn.security.WebSecurityConfig;
import com.linepro.modellbahn.service.impl.AchsfolgService;
import com.linepro.modellbahn.service.impl.AntriebService;
import com.linepro.modellbahn.service.impl.ArtikelService;
import com.linepro.modellbahn.service.impl.AufbauService;
import com.linepro.modellbahn.service.impl.BahnverwaltungService;
import com.linepro.modellbahn.service.impl.DecoderService;
import com.linepro.modellbahn.service.impl.DecoderTypService;
import com.linepro.modellbahn.service.impl.EmailService;
import com.linepro.modellbahn.service.impl.EnumsService;
import com.linepro.modellbahn.service.impl.EpochService;
import com.linepro.modellbahn.service.impl.GattungService;
import com.linepro.modellbahn.service.impl.HerstellerService;
import com.linepro.modellbahn.service.impl.KategorieService;
import com.linepro.modellbahn.service.impl.KupplungService;
import com.linepro.modellbahn.service.impl.LichtService;
import com.linepro.modellbahn.service.impl.MassstabService;
import com.linepro.modellbahn.service.impl.MotorTypService;
import com.linepro.modellbahn.service.impl.ProduktService;
import com.linepro.modellbahn.service.impl.ProtokollService;
import com.linepro.modellbahn.service.impl.SonderModellService;
import com.linepro.modellbahn.service.impl.SpurweiteService;
import com.linepro.modellbahn.service.impl.SteuerungService;
import com.linepro.modellbahn.service.impl.UserService;
import com.linepro.modellbahn.service.impl.VorbildService;
import com.linepro.modellbahn.service.impl.ZugService;
import com.linepro.modellbahn.service.impl.ZugTypService;
import com.linepro.modellbahn.swagger.SwaggerConfig;
import com.linepro.modellbahn.util.ErrorHandler;
import com.linepro.modellbahn.util.FileFinderImpl;
import com.linepro.modellbahn.util.FileStoreImpl;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@EnableJpaRepositories("com.linepro.modellbahn.repository") 
@EntityScan( basePackages = {"com.linepro.modellbahn.entity"} )
//@ComponentScan
@Import({
    // Logging
    BusinessLogger.class,
    LoggedAspect.class,
    RequestLoggingConfiguration.class,
    RequestLocaleFilter.class,

   // @Configuration
    ErrorHandler.class,
    MvcConfig.class,
    SwaggerConfig.class,

    // Utils
    FileFinderImpl.class,
    FileServiceImpl.class,
    FileStoreImpl.class,
    FileUploadHandlerImpl.class,
    MessageTranslatorImpl.class,

    // Security
    AuthorizationServerConfig.class,
    CorsConfig.class,
    CustomAccessTokenConverter.class,
    OAuth2ResourceServerConfig.class,
    UserController.class,
    WebSecurityConfig.class,

    // HATAEOS
    AchsfolgModelProcessor.class,

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
    LichtController.class,
    MassstabController.class,
    MotorTypController.class,
    ProduktController.class,
    ProtokollController.class,
    SonderModellController.class,
    SpurweiteController.class,
    SteuerungController.class,
    VorbildController.class,
    ZugController.class,
    ZugTypController.class
    
    // @Component
})
public class ModellbahnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModellbahnApplication.class, args);
	}
}

