package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component
public class DecoderTypCvModelMutator implements Mutator<DecoderTypCvModel,DecoderTypCv> {
    
    @Override
    public DecoderTypCv apply(DecoderTypCvModel source, DecoderTypCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setCv(source.getCv());
        }

        return applyFields(source, destination);
    }

    @Override
    public DecoderTypCv applyFields(DecoderTypCvModel source, DecoderTypCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setBezeichnung(source.getBezeichnung());
            destination.setMinimal(source.getMinimal());
            destination.setMaximal(source.getMaximal());
            destination.setWerkseinstellung(source.getWerkseinstellung());
        }
        
        return destination;
    }

    @Override
    public DecoderTypCv get() {
        return new DecoderTypCv();
    }
}
