package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

public class DecoderTypCvModelTranscriber implements Transcriber<DecoderTypCvModel,DecoderTypCv> {
    
    @Override
    public DecoderTypCv apply(DecoderTypCvModel source, DecoderTypCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setCv(source.getCv());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setMinimal(source.getMinimal());
            destination.setMaximal(source.getMaximal());
            destination.setWerkseinstellung(source.getWerkseinstellung());
        }
        
        return destination;
    }
}
