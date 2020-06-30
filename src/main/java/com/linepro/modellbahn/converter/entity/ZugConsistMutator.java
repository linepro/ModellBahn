package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ZugConsistModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ZugConsistMutator")
public class ZugConsistMutator implements Mutator<ZugConsist, ZugConsistModel> {

    @Autowired
    private ArtikelMutator artikelConverter;

    @Override
    public ZugConsistModel apply(ZugConsist source, ZugConsistModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final ArtikelModel artikel = artikelConverter.convert(source.getArtikel());

            destination.setZug(source.getZug().getName());
            destination.setPosition(source.getPosition());
            destination.setArtikelId(artikel.getArtikelId());
            destination.setHersteller(artikel.getHersteller());
            destination.setBestellNr(artikel.getBestellNr());
            destination.setBezeichnung(artikel.getBezeichnung());
            destination.setLange(artikel.getLange());
            destination.setBahnverwaltung(artikel.getBahnverwaltung());
            destination.setGattung(artikel.getGattung());
            destination.setBetreibsnummer(artikel.getBetreibsnummer());
            destination.setAbbildung(artikel.getAbbildung());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public ZugConsistModel get() {
        return new ZugConsistModel();
    }
}
