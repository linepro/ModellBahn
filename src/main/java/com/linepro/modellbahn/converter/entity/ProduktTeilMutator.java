package com.linepro.modellbahn.converter.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProduktTeilMutator implements Mutator<ProduktTeil,ProduktTeilModel> {

    @Autowired
    private final UnterKategorieMutator unterKategorieMutator;

    public ProduktTeilModel apply(ProduktTeil source, ProduktTeilModel destination, int depth) {
        destination.setHersteller(source.getProdukt().getHersteller().getName());
        destination.setBestellNr(source.getProdukt().getBestellNr());
        destination.setTeilHersteller(source.getTeil().getBestellNr());
        destination.setTeilBestellNr(source.getTeil().getBestellNr());
        destination.setBezeichnung(source.getTeil().getBezeichnung());
        destination.setUnterKategorie(unterKategorieMutator.convert(source.getTeil().getUnterKategorie()));
        destination.setAnzahl(source.getAnzahl());
        destination.setDeleted(source.getDeleted());
        return destination;
    }

    @Override
    public ProduktTeilModel get() {
        return new ProduktTeilModel();
    }
}
