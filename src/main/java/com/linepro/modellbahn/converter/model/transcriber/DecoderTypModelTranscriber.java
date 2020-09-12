package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypModelTranscriber implements Transcriber<DecoderTypModel, DecoderTyp> {

    private final HerstellerRepository herstellerRepository;

    private final ProtokollRepository protokollRepository;

    private final ItemLookup lookup;

    @Override
    public DecoderTyp apply(DecoderTypModel source, DecoderTyp destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getHersteller() == null) {
                destination.setHersteller(lookup.find(source.getHersteller(), herstellerRepository));
            }
            if (destination.getBestellNr() == null) {
                destination.setBestellNr(source.getBestellNr());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setIMax(source.getIMax());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setSound(source.getSound());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }
        
        return destination;
    }
}
