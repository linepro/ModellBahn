package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.UnterKategorieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProduktMutator implements Mutator<Produkt,ProduktModel> {

    @Autowired
    private final UnterKategorieMutator unterKategorieMutator;

    @Autowired
    private final ProduktTeilMutator teilMutator;

    @Autowired
    private final PathMutator pathMutator;
    
    @Override
    public ProduktModel apply(Produkt source, ProduktModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final UnterKategorieModel unterKategorie = unterKategorieMutator.convert(source.getUnterKategorie());
            
            destination.setHersteller(getCode(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setLange(source.getLange());
            destination.setBahnverwaltung(getCode(source.getBahnverwaltung()));
            destination.setKategorie(unterKategorie.getKategorie());
            destination.setUnterKategorie(unterKategorie.getName());
            destination.setMassstab(getCode(source.getMassstab()));
            destination.setSpurweite(getCode(source.getSpurweite()));
            destination.setBetreibsnummer(source.getBetreibsnummer());
            destination.setEpoch(getCode(source.getEpoch()));
            destination.setGattung(getCode(source.getGattung()));
            destination.setAchsfolg(getCode(source.getAchsfolg()));
            destination.setSondermodell(getCode(source.getSondermodell()));
            destination.setAufbau(getCode(source.getAufbau()));
            destination.setAbbildung(pathMutator.convert(source.getAbbildung()));
            destination.setBauzeit(source.getBauzeit());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setLicht(getCode(source.getLicht()));
            destination.setKupplung(getCode(source.getKupplung()));
            destination.setSteuerung(getCode(source.getSteuerung()));
            if (source.getDecoderTyp() != null) {
                destination.setDecoderTypHersteller(getCode(source.getDecoderTyp().getHersteller()));
                destination.setDecoderTypHersteller(source.getDecoderTyp().getBestellNr());
            }
            destination.setMotorTyp(getCode(source.getMotorTyp()));
            destination.setAnleitungen(pathMutator.convert(source.getAnleitungen()));
            destination.setExplosionszeichnung(pathMutator.convert(source.getExplosionszeichnung()));
            
            if (isAvailable(source.getTeilen())) {
                destination.setTeilen(source.getTeilen()
                                            .stream()
                                            .sorted()
                                            .map(t -> attempt(() -> teilMutator.convert(t)))
                                            .collect(success())
                                            .orElseThrow());
            }
        }
        
        return destination;
    }

    @Override
    public ProduktModel get() {
        return new ProduktModel();
    }
}
