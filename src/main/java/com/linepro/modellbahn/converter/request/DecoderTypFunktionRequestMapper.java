package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypFunktionRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.request.DecoderTypFunktionRequest;

@Component(PREFIX + "DecoderTypFunktionRequestMapper")
public class DecoderTypFunktionRequestMapper extends MapperImpl<DecoderTypFunktionRequest, DecoderTypFunktion> {

    @Autowired
    public DecoderTypFunktionRequestMapper(DecoderTypFunktionRequestTranscriber transcriber) {
        super(() -> new DecoderTypFunktion(), transcriber);
    }
}
