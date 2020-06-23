package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

@Component
public class DecoderCvMutator implements Mutator<DecoderCv, DecoderCvModel> {

    @Override
    public DecoderCvModel apply(DecoderCv source, DecoderCvModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoder().getDecoderId());
            destination.setCv(source.getCv().getCv());
            destination.setBezeichnung(source.getCv().getBezeichnung());
            destination.setMinimal(source.getCv().getMinimal());
            destination.setMaximal(source.getCv().getMaximal());
            destination.setWerkseinstellung(source.getCv().getWerkseinstellung());
            destination.setWert(source.getWert());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderCvModel get() {
        return new DecoderCvModel();
    }
}
