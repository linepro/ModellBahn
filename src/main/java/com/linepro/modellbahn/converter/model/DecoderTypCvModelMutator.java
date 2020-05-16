package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component
public class DecoderTypCvModelMutator implements Mutator<DecoderTypCvModel,DecoderTypCv> {
    
    public DecoderTypCv apply(DecoderTypCvModel source, DecoderTypCv destination, int depth) {
        destination.setCv(source.getCv());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setMinimal(source.getMinimal());
        destination.setMaximal(source.getMaximal());
        destination.setWerkseinstellung(source.getWerkseinstellung());
        return destination;
    }

    @Override
    public DecoderTypCv get() {
        return new DecoderTypCv();
    }
}
