package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DecoderModelMutator implements Mutator<DecoderModel, Decoder> {

    @Autowired
    private final ProtokollModelMutator protokollMutator;
    
    @Override
    public Decoder apply(DecoderModel source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoderId());
        }
        
        return applyFields(source, destination);
    }
    
    @Override
    public Decoder applyFields(DecoderModel source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStatus(source.getStatus());
            destination.setProtokoll(protokollMutator.convert(source.getProtokoll()));
            destination.setFahrstufe(source.getFahrstufe());
        }
        
        return destination;
    }

    @Override
    public Decoder get() {
        return new Decoder();
    }
}
