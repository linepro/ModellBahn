package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktDecoderTyp;
import com.linepro.modellbahn.model.ProduktDecoderTypModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ProduktDecoderTypTranscriber")
public class ProduktDecoderTypTranscriber implements Transcriber<ProduktDecoderTyp, ProduktDecoderTypModel> {

    @Override
    public ProduktDecoderTypModel apply(ProduktDecoderTyp source, ProduktDecoderTypModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getProdukt().getHersteller().getName());
            destination.setHerstellerBezeichnung(source.getProdukt().getHersteller().getBezeichnung());
            destination.setBestellNr(source.getProdukt().getBestellNr());
            destination.setDecoderTypHersteller(source.getDecoderTyp().getHersteller().getName());
            destination.setDecoderTypHerstellerBezeichnung(source.getDecoderTyp().getHersteller().getBezeichnung());
            destination.setDecoderTypBestellNr(source.getDecoderTyp().getBestellNr());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
