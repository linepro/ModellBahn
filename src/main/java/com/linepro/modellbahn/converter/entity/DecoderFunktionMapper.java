package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderFunktionTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Component(PREFIX + "DecoderFunktionMapper")
public class DecoderFunktionMapper extends MapperImpl<DecoderFunktion, DecoderFunktionModel> {

    public DecoderFunktionMapper() {
        super(() -> new DecoderFunktionModel(), new DecoderFunktionTranscriber());
    }
}
