package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "VorbildMutator")
public class VorbildMutator implements Mutator<Vorbild,VorbildModel> {
    
    @Autowired
    private PathMutator pathMutator;

    @Override
    public VorbildModel apply(Vorbild source, VorbildModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setGattung(source.getGattung().getName());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setKategorie(getCode(source.getUnterKategorie().getKategorie()));
            destination.setUnterKategorie(getCode(source.getUnterKategorie()));
            destination.setBahnverwaltung(getCode(source.getBahnverwaltung()));
            destination.setHersteller(source.getHersteller());
            destination.setBauzeit(source.getBauzeit());
            destination.setAnzahl(source.getAnzahl());
            destination.setBetreibsNummer(source.getBetreibsNummer());
            destination.setAntrieb(getCode(source.getAntrieb()));
            destination.setAchsfolg(getCode(source.getAchsfolg()));
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
            destination.setSitzplatzeKL1(source.getSitzplatzeKL1());
            destination.setSitzplatzeKL2(source.getSitzplatzeKL2());
            destination.setSitzplatzeKL3(source.getSitzplatzeKL3());
            destination.setSitzplatzeKL4(source.getSitzplatzeKL4());
            destination.setAufbau(source.getAufbau());
            destination.setTriebkopf(source.getTriebkopf());
            destination.setMittelwagen(source.getMittelwagen());
            destination.setDrehgestellBauart(source.getDrehgestellBauart());
            destination.setAbbildung(pathMutator.convert(source.getAbbildung()));
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public VorbildModel get() {
        return new VorbildModel();
    }
}
