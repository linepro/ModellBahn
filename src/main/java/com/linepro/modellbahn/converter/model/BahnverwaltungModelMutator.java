package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component(PREFIX + "BahnverwaltungModelMutator")
public class BahnverwaltungModelMutator extends MutatorImpl<BahnverwaltungModel, Bahnverwaltung> {

    public BahnverwaltungModelMutator() {
        super(() -> new Bahnverwaltung(), new NamedTranscriber<BahnverwaltungModel, Bahnverwaltung>() {
                @Override
                public Bahnverwaltung apply(BahnverwaltungModel source, Bahnverwaltung destination) {
                    destination.setLand(source.getLand());
        
                    return super.apply(source, destination);
                }
            });
    }

}
