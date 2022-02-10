package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.repository.lookup.HerstellerLookup;
import com.linepro.modellbahn.repository.lookup.ProtokollLookup;
import com.linepro.modellbahn.request.DecoderTypRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderTypRequestTranscriber")
public class DecoderTypRequestTranscriber implements Transcriber<DecoderTypRequest, DecoderTyp> {

    private final HerstellerLookup herstellerLookup;

    private final ProtokollLookup protokollLookup;

    @Override
    public DecoderTyp apply(DecoderTypRequest source, DecoderTyp destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getHersteller() == null) {
                herstellerLookup.find(source.getHersteller()).ifPresent(h -> destination.setHersteller(h));
            }
            if (destination.getBestellNr() == null) {
                destination.setBestellNr(source.getBestellNr());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setIMax(source.getIMax());
            protokollLookup.find(source.getProtokoll()).ifPresent(p -> destination.setProtokoll(p));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setAdress(source.getAdress());
            destination.setSpan(source.getSpan());
            destination.setSound(source.getSound());
            destination.setMotor(source.getMotor());
            destination.setOutputs(source.getOutputs());
            destination.setServos(source.getServos());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
