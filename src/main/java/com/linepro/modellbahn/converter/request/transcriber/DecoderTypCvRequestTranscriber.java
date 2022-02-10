package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypCvRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypCvRequestTranscriber")
public class DecoderTypCvRequestTranscriber implements Transcriber<DecoderTypCvRequest,DecoderTypCv> {

    private final DecoderTypLookup typLookup;

    @Override
    public DecoderTypCv apply(DecoderTypCvRequest source, DecoderTypCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderTyp() == null) {
                typLookup.find(source.getHersteller(), source.getBestellNr()).ifPresent(t -> destination.setDecoderTyp(t));
            }
            if (destination.getCv() == null) {
                destination.setCv(source.getCv());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setMinimal(source.getMinimal());
            destination.setMaximal(source.getMaximal());
            destination.setWerkseinstellung(source.getWerkseinstellung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
