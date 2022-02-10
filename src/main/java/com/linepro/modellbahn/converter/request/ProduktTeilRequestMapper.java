package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ProduktTeilRequestTranscriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.request.ProduktTeilRequest;

@Component(PREFIX + "ProduktTeilRequestMapper")
public class ProduktTeilRequestMapper extends MapperImpl<ProduktTeilRequest,ProduktTeil> {

    @Autowired
    public ProduktTeilRequestMapper(ProduktTeilRequestTranscriber transcriber) {
        super(() -> new ProduktTeil(), transcriber);
    }
}
