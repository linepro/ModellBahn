package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;

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
    
    public ProduktModel apply(Produkt source, ProduktModel destination, int depth) {
        destination.setHersteller(herstellerMutator.convert(source.getHersteller()));
        destination.setBestellNr(source.getBestellNr());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setUnterKategorie(unterKategorieMutator.convert(source.getUnterKategorie()));
        destination.setMassstab(massstabMutator.convert(source.getMassstab()));
        destination.setSpurweite(spurweiteMutator.convert(source.getSpurweite()));
        destination.setBetreibsnummer(source.getBetreibsnummer());
        destination.setEpoch(epochMutator.convert(source.getEpoch()));
        destination.setBahnverwaltung(bahnverwaltungMutator.convert(source.getBahnverwaltung()));
        destination.setGattung(gattungMutator.convert(source.getGattung()));
        destination.setBauzeit(source.getBauzeit());
        destination.setAchsfolg(achsfolgMutator.convert(source.getAchsfolg()));
        destination.setVorbild(vorbildMutator.convert(source.getVorbild()));
        destination.setAnmerkung(source.getAnmerkung());
        destination.setSondermodell(sondermodellMutator.convert(source.getSondermodell()));
        destination.setAufbau(aufbauMutator.convert(source.getAufbau()));
        destination.setLicht(lichtMutator.convert(source.getLicht()));
        destination.setKupplung(kupplungMutator.convert(source.getKupplung()));
        destination.setSteuerung(steuerungMutator.convert(source.getSteuerung()));
        destination.setDecoderTyp(decoderTypMutator.convert(source.getDecoderTyp()));
        destination.setMotorTyp(motorTypMutator.convert(source.getMotorTyp()));
        destination.setLange(source.getLange());
        destination.setAnleitungen(source.getAnleitungen());
        destination.setExplosionszeichnung(source.getExplosionszeichnung());
        destination.setAbbildung(source.getAbbildung());
        
        if (depth >= 0) {
            final int layer = depth-1;

            destination.setTeilen(source.getTeilen()
                                        .stream()
                                        .sorted()
                                        .map(t -> teilMutator.convert(t, layer))
                                        .filter(t -> t != null)
                                        .collect(Collectors.toList()));
        }
        
        return destination;
    }

    @Override
    public ProduktModel get() {
        return new ProduktModel();
    }
}
