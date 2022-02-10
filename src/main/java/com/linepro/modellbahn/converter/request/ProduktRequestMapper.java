package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ProduktRequestTranscriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.request.ProduktRequest;

@Component(PREFIX + "ProduktRequestMapper")
public class ProduktRequestMapper extends MapperImpl<ProduktRequest, Produkt> {

    @Autowired
    public ProduktRequestMapper(ProduktRequestTranscriber transcriber) {
        super(() -> new Produkt(), transcriber);
    }
}
