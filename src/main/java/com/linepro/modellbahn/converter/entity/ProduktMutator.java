package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProduktMutator implements Mutator<Produkt,ProduktModel> {

    @Autowired
    private final HerstellerMutator herstellerMutator;

    @Autowired
    private final UnterKategorieMutator unterKategorieMutator;
    
    @Autowired
    private final MassstabMutator massstabMutator;
    
    @Autowired
    private final SpurweiteMutator spurweiteMutator;
    
    @Autowired
    private final EpochMutator epochMutator;
    
    @Autowired
    private final BahnverwaltungMutator bahnverwaltungMutator;

    @Autowired
    private final GattungMutator gattungMutator;
    
    @Autowired
    private final AchsfolgMutator achsfolgMutator;
    
    @Autowired
    private final VorbildMutator vorbildMutator;
    
    @Autowired
    private final SondermodellMutator sondermodellMutator;
    
    @Autowired
    private final AufbauMutator aufbauMutator;
    
    @Autowired
    private final LichtMutator lichtMutator;
    
    @Autowired
    private final KupplungMutator kupplungMutator;
    
    @Autowired
    private final SteuerungMutator steuerungMutator;
    
    @Autowired
    private final DecoderTypMutator decoderTypMutator;
    
    @Autowired
    private final MotorTypMutator motorTypMutator;
    
    @Autowired
    private final ProduktTeilMutator teilMutator;
    
    public ProduktModel applyFields(Produkt source, ProduktModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            applySummary(source, destination);
            destination.setUnterKategorie(unterKategorieMutator.summarize(source.getUnterKategorie()));
            destination.setMassstab(massstabMutator.summarize(source.getMassstab()));
            destination.setSpurweite(spurweiteMutator.summarize(source.getSpurweite()));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(epochMutator.summarize(source.getEpoch()));
            destination.setBahnverwaltung(bahnverwaltungMutator.summarize(source.getBahnverwaltung()));
            destination.setGattung(gattungMutator.summarize(source.getGattung()));
            destination.setBauzeit(source.getBauzeit());
            destination.setAchsfolg(achsfolgMutator.summarize(source.getAchsfolg()));
            destination.setVorbild(vorbildMutator.summarize(source.getVorbild()));
            destination.setAnmerkung(source.getAnmerkung());
            destination.setSondermodell(sondermodellMutator.summarize(source.getSondermodell()));
            destination.setAufbau(aufbauMutator.summarize(source.getAufbau()));
            destination.setLicht(lichtMutator.summarize(source.getLicht()));
            destination.setKupplung(kupplungMutator.summarize(source.getKupplung()));
            destination.setSteuerung(steuerungMutator.summarize(source.getSteuerung()));
            destination.setDecoderTyp(decoderTypMutator.summarize(source.getDecoderTyp()));
            destination.setMotorTyp(motorTypMutator.summarize(source.getMotorTyp()));
            destination.setLange(source.getLange());
            destination.setAnleitungen(source.getAnleitungen());
            destination.setExplosionszeichnung(source.getExplosionszeichnung());
            destination.setAbbildung(source.getAbbildung());
            
            if (isAvailable(source.getTeilen())) {
                destination.setTeilen(source.getTeilen()
                                            .stream()
                                            .sorted()
                                            .map(t -> attempt(() -> teilMutator.convert(t)))
                                            .collect(new ResultCollector<>())
                                            .orElseThrow());
            }
        }
        
        return destination;
    }

    @Override
    public ProduktModel applySummary(Produkt source, ProduktModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(herstellerMutator.summarize(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
        }
        
        return destination;
    }

    @Override
    public ProduktModel get() {
        return new ProduktModel();
    }
}
