package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.DecoderTypAdressRequestTranscriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypAdressRequest;

@Component(PREFIX + "DecoderTypAdressRequestMapper")
public class DecoderTypAdressRequestMapper extends MapperImpl<DecoderTypAdressRequest, DecoderTypAdress> {

    @Autowired
    public DecoderTypAdressRequestMapper(DecoderTypLookup lookup) {
        super(() -> new DecoderTypAdress(), new DecoderTypAdressRequestTranscriber(lookup));
    }
}
