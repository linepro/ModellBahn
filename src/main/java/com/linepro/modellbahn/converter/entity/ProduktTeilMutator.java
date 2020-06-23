package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProduktTeilMutator implements Mutator<ProduktTeil,ProduktTeilModel> {

    @Override
    public ProduktTeilModel apply(ProduktTeil source, ProduktTeilModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getProdukt().getHersteller().getName());
            destination.setBestellNr(source.getProdukt().getBestellNr());
            destination.setTeilHersteller(source.getTeil().getBestellNr());
            destination.setTeilBestellNr(source.getTeil().getBestellNr());
            destination.setBezeichnung(source.getTeil().getBezeichnung());
            destination.setKategorie(getCode(source.getTeil().getUnterKategorie().getKategorie()));
            destination.setUnterKategorie(getCode(source.getTeil().getUnterKategorie()));
            destination.setAnzahl(source.getAnzahl());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public ProduktTeilModel get() {
        return new ProduktTeilModel();
    }
}
