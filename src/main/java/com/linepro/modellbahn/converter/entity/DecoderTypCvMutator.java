package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component
public class DecoderTypCvMutator implements Mutator<DecoderTypCv,DecoderTypCvModel> {
    
    @Override
    public DecoderTypCvModel applyFields(DecoderTypCv source, DecoderTypCvModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getDecoderTyp().getHersteller().getName());
            destination.setBestellNr(source.getDecoderTyp().getBestellNr());
            destination.setCv(source.getCv());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setMinimal(source.getMinimal());
            destination.setMaximal(source.getMaximal());
            destination.setWerkseinstellung(source.getWerkseinstellung());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderTypCvModel get() {
        return new DecoderTypCvModel();
    }
}
