package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ProduktTeilTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

@Component(PREFIX + "ProduktTeilMapper")
public class ProduktTeilMapper extends MapperImpl<ProduktTeil, ProduktTeilModel> {

    public ProduktTeilMapper() {
        super(() -> new ProduktTeilModel(), new ProduktTeilTranscriber());
    }
}
