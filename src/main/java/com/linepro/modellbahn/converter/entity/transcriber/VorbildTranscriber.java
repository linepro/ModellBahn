package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

public class VorbildTranscriber extends NamedAbbildungTranscriber<Vorbild, VorbildModel> {

    public VorbildTranscriber(com.linepro.modellbahn.converter.PathMapper PathMapper) {
        super(PathMapper);
    }

    @Override
    public VorbildModel apply(Vorbild source, VorbildModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setGattung(source.getGattung().getName());
            destination.setKategorie(getCode(source.getUnterKategorie().getKategorie()));
            destination.setUnterKategorie(getCode(source.getUnterKategorie()));
            destination.setBahnverwaltung(getCode(source.getBahnverwaltung()));
            destination.setHersteller(source.getHersteller());
            destination.setBauzeit(source.getBauzeit());
            destination.setMenge(source.getMenge());
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
        }

        return super.apply(source, destination);
    }
}
