package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungMutator implements Mutator<Anderung, AnderungModel> {

    public AnderungMutator() {
    }

    @Override
    public AnderungModel apply(Anderung source, AnderungModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setArtikelId(source.getArtikel().getArtikelId());
            destination.setAnderungId(source.getAnderungId());
            destination.setAnderungsDatum(source.getAnderungsDatum());
            destination.setAnderungsTyp(source.getAnderungsTyp());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStuck(source.getStuck());
        }
        
        return destination;
    }

    @Override
    public AnderungModel get() {
        return new AnderungModel();
    }

}
