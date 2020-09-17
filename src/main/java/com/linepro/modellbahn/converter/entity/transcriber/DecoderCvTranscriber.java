package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

public class DecoderCvTranscriber implements Transcriber<DecoderCv, DecoderCvModel> {

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
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
