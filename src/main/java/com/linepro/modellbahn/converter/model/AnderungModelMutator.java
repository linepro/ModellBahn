package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungModelMutator implements Mutator<AnderungModel, Anderung> {

    public Anderung apply(AnderungModel source, Anderung destination, int depth) {
        destination.setAnderungId(source.getAnderungId());
        destination.setAnderungsDatum(source.getAnderungsDatum());
        destination.setAnderungsTyp(source.getAnderungsTyp());
        destination.setAnmerkung(source.getAnmerkung());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }

    @Override
    public Anderung get() {
        return new Anderung();
    }
}
