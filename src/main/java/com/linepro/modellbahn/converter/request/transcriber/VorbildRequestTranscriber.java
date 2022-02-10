package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.lookup.AchsfolgLookup;
import com.linepro.modellbahn.repository.lookup.AntriebLookup;
import com.linepro.modellbahn.repository.lookup.BahnverwaltungLookup;
import com.linepro.modellbahn.repository.lookup.GattungLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.request.VorbildRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "VorbildRequestTranscriber")
public class VorbildRequestTranscriber extends NamedRequestTranscriber<VorbildRequest, Vorbild> {

        private final GattungLookup gattungLookup;

        private final UnterKategorieLookup unterKategorieLookup;

        private final AntriebLookup antriebLookup;

        private final BahnverwaltungLookup bahnverwaltungLookup;

        private final AchsfolgLookup achsfolgLookup;

        public Vorbild apply(VorbildRequest source, Vorbild destination) {
            if (isAvailable(source) && isAvailable(destination)) {
                destination.setGattung(gattungLookup.find(source.getGattung()).orElse(null));
                unterKategorieLookup.find(source.getKategorie(), source.getUnterKategorie()).ifPresent(u -> destination.setUnterKategorie(u));
                destination.setBahnverwaltung(bahnverwaltungLookup.find(source.getBahnverwaltung()).orElse(null));
                destination.setHersteller(source.getHersteller());
                destination.setBauzeit(source.getBauzeit());
                destination.setMenge(source.getMenge());
                destination.setBetreibsNummer(source.getBetreibsNummer());
                destination.setAntrieb(antriebLookup.find(source.getAntrieb()).orElse(null));
                destination.setAchsfolg(achsfolgLookup.find(source.getAchsfolg()).orElse(null));
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
