package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypModelMutator")
public class DecoderTypModelMutator implements Mutator<DecoderTypModel, DecoderTyp> {

    @Autowired
    private final HerstellerRepository herstellerRepository;

    @Autowired
    private final ProtokollRepository protokollRepository;

    @Autowired
    private final ItemLookup lookup;

    @Override
    public DecoderTyp apply(DecoderTypModel source, DecoderTyp destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setHersteller(lookup.find(source.getHersteller(), herstellerRepository));
            destination.setIMax(source.getIMax());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setSound(source.getSound());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
        }
        
        return destination;
    }

    @Override
    public DecoderTyp get() {
        return new DecoderTyp();
    }
}
