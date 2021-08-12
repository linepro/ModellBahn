package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderRequestTranscriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.request.DecoderRequest;

@Component(PREFIX + "DecoderRequestMapper")
public class DecoderRequestMapper extends MapperImpl<DecoderRequest, Decoder> {

    @Autowired
    public DecoderRequestMapper(DecoderTypLookup typLookup, ProtokollRepository protokollRepository, ItemLookup lookup) {
        super(() -> new Decoder(), new DecoderRequestTranscriber(typLookup, protokollRepository, lookup));
    }
}