package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderFunktionRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.request.DecoderFunktionRequest;

@Component(PREFIX + "DecoderFunktionRequestMapper")
public class DecoderFunktionRequestMapper extends MapperImpl<DecoderFunktionRequest,DecoderFunktion> {

    @Autowired
    public DecoderFunktionRequestMapper(DecoderFunktionRequestTranscriber transcriber) {
        super(() -> new DecoderFunktion(), transcriber);
    }
}
