package com.linepro.modellbahn.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.UnterKategorieRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class VorbildModelMutator implements Mutator<VorbildModel, Vorbild> {
    
    @Autowired
    private final UnterKategorieRepository unterKategorieRepository;
    
    @Autowired
    private final AntriebRepository antriebRepository;
     
    @Autowired
    private final BahnverwaltungRepository bahnverwaltungRepository;
    
    @Autowired
    private final AchsfolgRepository achsfolgRepository;

    @Autowired
    private final ItemLookup lookup;
    
    public Vorbild apply(VorbildModel source, Vorbild destination, int depth) {
        destination.setUnterKategorie(lookup.find(source.getUnterKategorie(), unterKategorieRepository));
        destination.setBahnverwaltung(lookup.find(source.getBahnverwaltung(), bahnverwaltungRepository));
        destination.setHersteller(source.getHersteller());
        destination.setBauzeit(source.getBauzeit());
        destination.setAnzahl(source.getAnzahl());
        destination.setBetreibsNummer(source.getBetreibsNummer());
        destination.setAntrieb(lookup.find(source.getAntrieb(), antriebRepository));
        destination.setAchsfolg(lookup.find(source.getAchsfolg(), achsfolgRepository));
        destination.setAnfahrzugkraft(source.getAnfahrzugkraft());
        destination.setLeistung(source.getLeistung());
        destination.setDienstgewicht(source.getDienstgewicht());
        destination.setGeschwindigkeit(source.getGeschwindigkeit());
        destination.setLange(source.getLange());
        destination.setAusserdienst(source.getAusserdienst());
        destination.setDmTreibrad(source.getDmTreibrad());
        destination.setDmLaufradVorn(source.getDmLaufradVorn());
        destination.setDmLaufradHinten(source.getDmLaufradHinten());
        destination.setZylinder(source.getZylinder());
        destination.setDmZylinder(source.getDmZylinder());
        destination.setKolbenhub(source.getKolbenhub());
        destination.setKesseluberdruck(source.getKesseluberdruck());
        destination.setRostflache(source.getRostflache());
        destination.setUberhitzerflache(source.getUberhitzerflache());
        destination.setWasservorrat(source.getWasservorrat());
        destination.setVerdampfung(source.getVerdampfung());
        destination.setFahrmotoren(source.getFahrmotoren());
        destination.setMotorbauart(source.getMotorbauart());
        destination.setLeistungsUbertragung(source.getLeistungsUbertragung());
        destination.setReichweite(source.getReichweite());
        destination.setKapazitat(source.getKapazitat());
        destination.setKlasse(source.getKlasse());
        destination.setSitzPlatzeKL1(source.getSitzPlatzeKL1());
        destination.setSitzPlatzeKL2(source.getSitzPlatzeKL2());
        destination.setSitzPlatzeKL3(source.getSitzPlatzeKL3());
        destination.setSitzPlatzeKL4(source.getSitzPlatzeKL4());
        destination.setAufbau(source.getAufbau());
        destination.setTriebkopf(source.getTriebkopf());
        destination.setMittelwagen(source.getMittelwagen());
        destination.setDrehgestellBauart(source.getDrehgestellBauart());
        destination.setAbbildung(source.getAbbildung());
        return destination;
    }

    @Override
    public Vorbild get() {
        return new Vorbild();
    }
}
