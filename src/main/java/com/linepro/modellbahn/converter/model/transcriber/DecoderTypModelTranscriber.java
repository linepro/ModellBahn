package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

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
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setHersteller(lookup.find(source.getHersteller(), herstellerRepository));
            destination.setIMax(source.getIMax());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setSound(source.getSound());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
        }
        
        return destination;
    }
}
