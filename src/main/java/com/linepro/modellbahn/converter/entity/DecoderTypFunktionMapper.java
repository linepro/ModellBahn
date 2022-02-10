package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypFunktionTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component(PREFIX + "DecoderTypFunktionMapper")
public class DecoderTypFunktionMapper extends MapperImpl<DecoderTypFunktion,DecoderTypFunktionModel> {

    public DecoderTypFunktionMapper(DecoderTypFunktionTranscriber transcriber) {
        super(() -> new DecoderTypFunktionModel(), transcriber);
    }
}
