package com.linepro.modellbahn.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
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
import com.linepro.modellbahn.repository.SonderModelRepository;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.UnterKategorieRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.VorbildLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProduktModelMutator implements Mutator<ProduktModel, Produkt> {

    @Autowired
    private final HerstellerRepository herstellerRepository;

    @Autowired
    private final UnterKategorieRepository unterKategorieRepository;

    @Autowired
    private final MassstabRepository massstabRepository;

    @Autowired
    private final SpurweiteRepository spurweiteRepository;

    @Autowired
    private final EpochRepository epochRepository;

    @Autowired
    private final BahnverwaltungRepository bahnverwaltungRepository;

    @Autowired
    private final GattungRepository gattungRepository;

    @Autowired
    private final AchsfolgRepository achsfolgRepository;

    @Autowired
    private final VorbildLookup vorbildLookup;

    @Autowired
    private final SonderModelRepository sonderModelRepository;

    @Autowired
    private final AufbauRepository aufbauRepository;

    @Autowired
    private final LichtRepository lichtRepository;

    @Autowired
    private final KupplungRepository kupplungRepository;

    @Autowired
    private final SteuerungRepository steuerungRepository;

    @Autowired
    private final DecoderTypLookup decoderTypLookup;

    @Autowired
    private final MotorTypRepository motorTypRepository;

    @Autowired
    private final ItemLookup lookup;

    public Produkt apply(ProduktModel source, Produkt destination, int depth) {
        if (depth > 0) {
            destination.setHersteller(lookup.find(source.getHersteller(), herstellerRepository));
            destination.setBestellNr(source.getBestellNr());
        }
        destination.setBezeichnung(source.getBezeichnung());
        destination.setUnterKategorie(lookup.find(source.getUnterKategorie(), unterKategorieRepository));
        destination.setMassstab(lookup.find(source.getMassstab(), massstabRepository));
        destination.setSpurweite(lookup.find(source.getSpurweite(), spurweiteRepository));
        destination.setBetreibsnummer(source.getBetreibsnummer());
        destination.setEpoch(lookup.find(source.getEpoch(), epochRepository));
        destination.setBahnverwaltung(lookup.find(source.getBahnverwaltung(), bahnverwaltungRepository));
        destination.setGattung(lookup.find(source.getGattung(), gattungRepository));
        destination.setBauzeit(source.getBauzeit());
        destination.setAchsfolg(lookup.find(source.getAchsfolg(), achsfolgRepository));
        destination.setVorbild(vorbildLookup.find(source.getVorbild()));
        destination.setAnmerkung(source.getAnmerkung());
        destination.setSonderModel(lookup.find(source.getSonderModel(), sonderModelRepository));
        destination.setAufbau(lookup.find(source.getAufbau(), aufbauRepository));
        destination.setLicht(lookup.find(source.getLicht(), lichtRepository));
        destination.setKupplung(lookup.find(source.getKupplung(), kupplungRepository));
        destination.setSteuerung(lookup.find(source.getSteuerung(), steuerungRepository));
        destination.setDecoderTyp(decoderTypLookup.find(source.getDecoderTyp()));
        destination.setMotorTyp(lookup.find(source.getMotorTyp(),motorTypRepository));
        destination.setLange(source.getLange());
        //destination.setAnleitungen(source.getAnleitungen());
        //destination.setExplosionszeichnung(source.getExplosionszeichnung());
        //destination.setAbbildung(source.getAbbildung());
        return destination;
    }

    @Override
    public Produkt get() {
        return new Produkt();
    }
}
