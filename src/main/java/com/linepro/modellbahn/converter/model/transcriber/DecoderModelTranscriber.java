package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderModelTranscriber implements Transcriber<DecoderModel, Decoder> {

    private final ProtokollRepository protokollRepository;

    private final ItemLookup lookup;
  
    @Override
    public Decoder apply(DecoderModel source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoderId());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStatus(source.getStatus());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
            destination.setFahrstufe(source.getFahrstufe());
        }
        
        return destination;
    }
}
