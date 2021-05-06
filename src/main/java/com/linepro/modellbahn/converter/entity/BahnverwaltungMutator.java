package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.BahnverwaltungTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component(PREFIX + "BahnverwaltungMutator")
public class BahnverwaltungMutator extends MutatorImpl<Bahnverwaltung, BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungMutator(PathMutator pathMutator) {
        super(() -> new BahnverwaltungModel(), new BahnverwaltungTranscriber(pathMutator));
    }

    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }

}
