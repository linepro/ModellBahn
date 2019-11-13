package com.linepro.modellbahn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.linepro.modellbahn.model.util.DecoderCreator;
import com.linepro.modellbahn.rest.service.AchsfolgService;
import com.linepro.modellbahn.rest.service.AntriebService;
import com.linepro.modellbahn.rest.service.ArtikelService;
import com.linepro.modellbahn.rest.service.AufbauService;
import com.linepro.modellbahn.rest.service.BahnverwaltungService;
import com.linepro.modellbahn.rest.service.DecoderService;
import com.linepro.modellbahn.rest.service.DecoderTypService;
import com.linepro.modellbahn.rest.service.EnumsService;
import com.linepro.modellbahn.rest.service.EpochService;
import com.linepro.modellbahn.rest.service.GattungService;
import com.linepro.modellbahn.rest.service.HerstellerService;
import com.linepro.modellbahn.rest.service.HttpService;
import com.linepro.modellbahn.rest.service.KategorieService;
import com.linepro.modellbahn.rest.service.KupplungService;
import com.linepro.modellbahn.rest.service.LandService;
import com.linepro.modellbahn.rest.service.LichtService;
import com.linepro.modellbahn.rest.service.MassstabService;
import com.linepro.modellbahn.rest.service.MotorTypService;
import com.linepro.modellbahn.rest.service.ProduktService;
import com.linepro.modellbahn.rest.service.ProtokollService;
import com.linepro.modellbahn.rest.service.SonderModellService;
import com.linepro.modellbahn.rest.service.SpurweiteService;
import com.linepro.modellbahn.rest.service.SteuerungService;
import com.linepro.modellbahn.rest.service.VorbildService;
import com.linepro.modellbahn.rest.service.WahrungService;
import com.linepro.modellbahn.rest.service.ZugService;
import com.linepro.modellbahn.rest.service.ZugTypService;
import com.linepro.modellbahn.util.DBPopulator;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories("com.linepro.modellbahn.persistence.repository") 
@EntityScan( basePackages = {"com.linepro.modellbahn.model.impl"} )
@Import({
    //SwaggerConfig.class,
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
	LandService.class,
	LichtService.class,
	MassstabService.class,
	MotorTypService.class,
	ProduktService.class,
	ProtokollService.class,
	SonderModellService.class,
	SpurweiteService.class,
	SteuerungService.class,
	VorbildService.class,
	WahrungService.class,
	ZugService.class,
	ZugTypService.class,
	HttpService.class,
	DecoderCreator.class,
    DBPopulator.class
})
@EnableWebMvc
public class ModellbahnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModellbahnApplication.class, args);
	}
}

