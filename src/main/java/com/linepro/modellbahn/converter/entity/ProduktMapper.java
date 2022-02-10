package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ProduktTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;

@Component(PREFIX + "ProduktMapper")
public class ProduktMapper extends MapperImpl<Produkt, ProduktModel> {

    @Autowired
    public ProduktMapper(ProduktTranscriber transcriber) {
        super(() -> new ProduktModel(), transcriber);
    }
}
