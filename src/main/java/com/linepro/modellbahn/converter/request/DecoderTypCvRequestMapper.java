package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypCvRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypCvRequest;

@Component(PREFIX + "DecoderTypCvRequestMapper")
public class DecoderTypCvRequestMapper extends MapperImpl<DecoderTypCvRequest, DecoderTypCv> {

    @Autowired
    public DecoderTypCvRequestMapper(DecoderTypLookup lookup) {
        super(() -> new DecoderTypCv(), new DecoderTypCvRequestTranscriber(lookup));
    }
}
