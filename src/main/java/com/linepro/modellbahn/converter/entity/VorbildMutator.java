package com.linepro.modellbahn.converter.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class VorbildMutator implements Mutator<Vorbild,VorbildModel> {
    
    @Autowired
    private final UnterKategorieMutator unterKategorieMutator;
    
    @Autowired
    private final AntriebMutator antriebMutator;
     
    @Autowired
    private final BahnverwaltungMutator bahnverwaltungMutator;
    
    @Autowired
    private final AchsfolgMutator achsfolgMutator;

    public VorbildModel apply(Vorbild source, VorbildModel destination, int depth) {
        destination.setGattung(source.getGattung().getName());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setUnterKategorie(unterKategorieMutator.convert(source.getUnterKategorie()));
        destination.setBahnverwaltung(bahnverwaltungMutator.convert(source.getBahnverwaltung()));
        destination.setHersteller(source.getHersteller());
        destination.setBauzeit(source.getBauzeit());
        destination.setAnzahl(source.getAnzahl());
        destination.setBetreibsNummer(source.getBetreibsNummer());
        destination.setAntrieb(antriebMutator.convert(source.getAntrieb()));
        destination.setAchsfolg(achsfolgMutator.convert(source.getAchsfolg()));
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
        destination.setDeleted(source.getDeleted());
        return destination;
    }

    @Override
    public VorbildModel get() {
        return new VorbildModel();
    }
}
