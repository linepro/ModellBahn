package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderAdressRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderAdressRequest;

@Component(PREFIX + "DecoderAdressRequestMapper")
public class DecoderAdressRequestMapper extends MapperImpl<DecoderAdressRequest,DecoderAdress> {

    @Autowired
    public DecoderAdressRequestMapper(DecoderLookup decoderLookup, DecoderTypAdressRepository adressLookup) {
        super(() -> new DecoderAdress(), new DecoderAdressRequestTranscriber(decoderLookup, adressLookup));
    }
}
