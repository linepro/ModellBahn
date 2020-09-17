package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.AufbauRepository;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.repository.EpochRepository;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.MassstabRepository;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.SondermodellRepository;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.UnterKategorieLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktModelTranscriber implements Transcriber<ProduktModel, Produkt> {

    private final HerstellerRepository herstellerRepository;

    private final UnterKategorieLookup unterKategorieLookup;

    private final MassstabRepository massstabRepository;

    private final SpurweiteRepository spurweiteRepository;

    private final EpochRepository epochRepository;

    private final BahnverwaltungRepository bahnverwaltungRepository;

    private final GattungRepository gattungRepository;

    private final AchsfolgRepository achsfolgRepository;

    private final VorbildRepository vorbildRepository;

    private final SondermodellRepository sondermodellRepository;

    private final AufbauRepository aufbauRepository;

    private final LichtRepository lichtRepository;

    private final KupplungRepository kupplungRepository;

    private final SteuerungRepository steuerungRepository;

    private final DecoderTypLookup decoderTypLookup;

    private final MotorTypRepository motorTypRepository;

    private final ItemLookup lookup;

    @Override
    public Produkt apply(ProduktModel source, Produkt destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(lookup.find(source.getHersteller(), herstellerRepository));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setUnterKategorie(unterKategorieLookup.find(source.getKategorie(), source.getUnterKategorie()));
            destination.setMassstab(lookup.find(source.getMassstab(), massstabRepository));
            destination.setSpurweite(lookup.find(source.getSpurweite(), spurweiteRepository));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(lookup.find(source.getEpoch(), epochRepository));
            destination.setBahnverwaltung(lookup.find(source.getBahnverwaltung(), bahnverwaltungRepository));
            destination.setGattung(lookup.find(source.getGattung(), gattungRepository));
            destination.setBauzeit(source.getBauzeit());
            destination.setAchsfolg(lookup.find(source.getAchsfolg(), achsfolgRepository));
            destination.setVorbild(lookup.find(source.getGattung(), vorbildRepository));
            destination.setAnmerkung(source.getAnmerkung());
            destination.setSondermodell(lookup.find(source.getSondermodell(), sondermodellRepository));
            destination.setAufbau(lookup.find(source.getAufbau(), aufbauRepository));
            destination.setLicht(lookup.find(source.getLicht(), lichtRepository));
            destination.setKupplung(lookup.find(source.getKupplung(), kupplungRepository));
            destination.setSteuerung(lookup.find(source.getSteuerung(), steuerungRepository));
            destination.setDecoderTyp(decoderTypLookup.find(source.getDecoderTypHersteller(), source.getDecoderTypBestellNr()));
            destination.setMotorTyp(lookup.find(source.getMotorTyp(),motorTypRepository));
            destination.setLange(source.getLange());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
