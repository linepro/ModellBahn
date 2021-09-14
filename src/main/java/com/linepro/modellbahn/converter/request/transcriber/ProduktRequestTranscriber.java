package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.lookup.AchsfolgLookup;
import com.linepro.modellbahn.repository.lookup.AufbauLookup;
import com.linepro.modellbahn.repository.lookup.BahnverwaltungLookup;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.EpochLookup;
import com.linepro.modellbahn.repository.lookup.GattungLookup;
import com.linepro.modellbahn.repository.lookup.HerstellerLookup;
import com.linepro.modellbahn.repository.lookup.KupplungLookup;
import com.linepro.modellbahn.repository.lookup.LichtLookup;
import com.linepro.modellbahn.repository.lookup.MassstabLookup;
import com.linepro.modellbahn.repository.lookup.MotorTypLookup;
import com.linepro.modellbahn.repository.lookup.SondermodellLookup;
import com.linepro.modellbahn.repository.lookup.SpurweiteLookup;
import com.linepro.modellbahn.repository.lookup.SteuerungLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;
import com.linepro.modellbahn.repository.lookup.VorbildLookup;
import com.linepro.modellbahn.request.ProduktRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktRequestTranscriber implements Transcriber<ProduktRequest, Produkt> {

    private final HerstellerLookup herstellerLookup;

    private final UnterKategorieLookup unterKategorieLookup;

    private final MassstabLookup massstabLookup;

    private final SpurweiteLookup spurweiteLookup;

    private final EpochLookup epochLookup;

    private final BahnverwaltungLookup bahnverwaltungLookup;

    private final GattungLookup gattungLookup;

    private final AchsfolgLookup achsfolgLookup;

    private final VorbildLookup vorbildLookup;

    private final SondermodellLookup sondermodellLookup;

    private final AufbauLookup aufbauLookup;

    private final LichtLookup lichtLookup;

    private final KupplungLookup kupplungLookup;

    private final SteuerungLookup steuerungLookup;

    private final DecoderTypLookup decoderTypLookup;

    private final MotorTypLookup motorTypLookup;

    @Override
    public Produkt apply(ProduktRequest source, Produkt destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            herstellerLookup.find(source.getHersteller()).ifPresent(h -> destination.setHersteller(h));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            unterKategorieLookup.find(source.getKategorie(), source.getUnterKategorie()).ifPresent(u -> destination.setUnterKategorie(u));
            destination.setMassstab(massstabLookup.find(source.getMassstab()).orElse(null));
            destination.setSpurweite(spurweiteLookup.find(source.getSpurweite()).orElse(null));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(epochLookup.find(source.getEpoch()).orElse(null));
            destination.setBahnverwaltung(bahnverwaltungLookup.find(source.getBahnverwaltung()).orElse(null));
            destination.setGattung(gattungLookup.find(source.getGattung()).orElse(null));
            destination.setBauzeit(source.getBauzeit());
            destination.setAchsfolg(achsfolgLookup.find(source.getAchsfolg()).orElse(null));
            destination.setVorbild(vorbildLookup.find(source.getGattung()).orElse(null));
            destination.setAnmerkung(source.getAnmerkung());
            destination.setSondermodell(sondermodellLookup.find(source.getSondermodell()).orElse(null));
            destination.setAufbau(aufbauLookup.find(source.getAufbau()).orElse(null));
            destination.setLicht(lichtLookup.find(source.getLicht()).orElse(null));
            destination.setKupplung(kupplungLookup.find(source.getKupplung()).orElse(null));
            destination.setSteuerung(steuerungLookup.find(source.getSteuerung()).orElse(null));
            destination.setDecoderTyp(decoderTypLookup.find(source.getDecoderTypHersteller(), source.getDecoderTypBestellNr()).orElse(null));
            destination.setMotorTyp(motorTypLookup.find(source.getMotorTyp()).orElse(null));
            destination.setLange(source.getLange());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
