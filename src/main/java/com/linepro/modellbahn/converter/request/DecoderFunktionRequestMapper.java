package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderFunktionRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.DecoderTypFunktionLookup;
import com.linepro.modellbahn.request.DecoderFunktionRequest;

@Component(PREFIX + "DecoderFunktionRequestMapper")
public class DecoderFunktionRequestMapper extends MapperImpl<DecoderFunktionRequest,DecoderFunktion> {

    @Autowired
    public DecoderFunktionRequestMapper(DecoderLookup decoderLookup, DecoderTypFunktionLookup funktionLookup) {
        super(() -> new DecoderFunktion(), new DecoderFunktionRequestTranscriber(decoderLookup, funktionLookup));
    }
}
