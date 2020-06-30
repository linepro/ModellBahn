package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderModelMutator")
public class DecoderModelMutator implements Mutator<DecoderModel, Decoder> {

    @Autowired
    private final ProtokollRepository protokollRepository;

    @Autowired
    private final ItemLookup lookup;
  
    @Override
    public Decoder apply(DecoderModel source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoderId());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStatus(source.getStatus());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
            destination.setFahrstufe(source.getFahrstufe());
        }
        
        return destination;
    }

    @Override
    public Decoder get() {
        return new Decoder();
    }
}
