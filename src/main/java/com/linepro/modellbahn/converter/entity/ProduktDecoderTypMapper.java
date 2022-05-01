package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ProduktDecoderTypTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ProduktDecoderTyp;
import com.linepro.modellbahn.model.ProduktDecoderTypModel;

@Component(PREFIX + "ProduktDecoderTypMapper")
public class ProduktDecoderTypMapper extends MapperImpl<ProduktDecoderTyp, ProduktDecoderTypModel> {

    public ProduktDecoderTypMapper(ProduktDecoderTypTranscriber transcriber) {
        super(() -> new ProduktDecoderTypModel(), transcriber);
    }
}
