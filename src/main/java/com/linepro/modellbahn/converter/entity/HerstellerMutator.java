package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component
public class HerstellerMutator extends MutatorImpl<Hersteller, HerstellerModel> {

    public HerstellerMutator() {
        super(() -> new HerstellerModel(), new NamedTranscriber<Hersteller, HerstellerModel>());
    }

    @Override
    public HerstellerModel apply(Hersteller source, HerstellerModel destination) {
        destination.setName(source.getName());
        destination.setLand(source.getLand());
        destination.setTelefon(source.getTelefon());
        destination.setUrl(source.getUrl());

        return super.apply(source, destination);
    }

}
