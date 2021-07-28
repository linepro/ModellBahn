package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderCvRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderCvRequest;

@Component(PREFIX + "DecoderCvRequestMapper")
public class DecoderCvRequestMapper extends MapperImpl<DecoderCvRequest,DecoderCv> {

    @Autowired
    public DecoderCvRequestMapper(DecoderLookup decoderLookup, DecoderTypCvRepository cvLookup) {
        super(() -> new DecoderCv(), new DecoderCvRequestTranscriber(decoderLookup, cvLookup));
    }
}
