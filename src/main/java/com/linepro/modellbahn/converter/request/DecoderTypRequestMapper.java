package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.request.DecoderTypRequest;

@Component(PREFIX + "DecoderTypRequestMapper")
public class DecoderTypRequestMapper extends MapperImpl<DecoderTypRequest, DecoderTyp> {

    @Autowired
    public DecoderTypRequestMapper(DecoderTypRequestTranscriber transcriber) {
        super(()-> new DecoderTyp(), transcriber);
    }
}
