package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungModelMutator implements Mutator<AnderungModel, Anderung> {

    @Override
    public Anderung apply(AnderungModel source, Anderung destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setAnderungId(source.getAnderungId());
        }

        return applyFields(source, destination);
    }


    @Override
    public Anderung applyFields(AnderungModel source, Anderung destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setAnderungsDatum(source.getAnderungsDatum());
            destination.setAnderungsTyp(source.getAnderungsTyp());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBezeichnung(source.getBezeichnung());
        }

        return destination;
    }

    @Override
    public Anderung get() {
        return new Anderung();
    }
}
