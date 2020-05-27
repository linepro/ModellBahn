package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component
public class BahnverwaltungMutator extends MutatorImpl<Bahnverwaltung, BahnverwaltungModel> {

    public BahnverwaltungMutator() {
        super(() -> new BahnverwaltungModel(), new NamedTranscriber<Bahnverwaltung, BahnverwaltungModel>());
    }

    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());
        return super.apply(source, destination);
    }

}
