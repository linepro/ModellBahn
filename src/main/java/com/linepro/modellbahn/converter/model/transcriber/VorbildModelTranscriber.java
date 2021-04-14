package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VorbildModelTranscriber extends NamedModelTranscriber<VorbildModel, Vorbild> {

        private final GattungRepository gattungRepository;

        private final UnterKategorieLookup unterKategorieLookup;

        private final AntriebRepository antriebRepository;

        private final BahnverwaltungRepository bahnverwaltungRepository;

        private final AchsfolgRepository achsfolgRepository;

        private final ItemLookup lookup;

        public Vorbild apply(VorbildModel source, Vorbild destination) {
            if (isAvailable(source) && isAvailable(destination)) {
                destination.setGattung(lookup.find(source.getGattung(), gattungRepository));
                destination.setUnterKategorie(unterKategorieLookup.find(source.getKategorie(), source.getUnterKategorie()));
                destination.setBahnverwaltung(lookup.find(source.getBahnverwaltung(), bahnverwaltungRepository));
                destination.setHersteller(source.getHersteller());
                destination.setBauzeit(source.getBauzeit());
                destination.setMenge(source.getMenge());
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
                destination.setSitzplatzeKL1(source.getSitzplatzeKL1());
                destination.setSitzplatzeKL2(source.getSitzplatzeKL2());
                destination.setSitzplatzeKL3(source.getSitzplatzeKL3());
                destination.setSitzplatzeKL4(source.getSitzplatzeKL4());
                destination.setAufbau(source.getAufbau());
                destination.setTriebkopf(source.getTriebkopf());
                destination.setMittelwagen(source.getMittelwagen());
                destination.setDrehgestellBauart(source.getDrehgestellBauart());
                destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
            }

            return super.apply(source, destination);
        }
}
