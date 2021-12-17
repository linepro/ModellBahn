package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

@Component(PREFIX + "DecoderTypMapper")
public class DecoderTypMapper extends MapperImpl<DecoderTyp, DecoderTypModel> {

    @Autowired
    public DecoderTypMapper(DecoderTypCvMapper cvMapper, DecoderTypFunktionMapper funktionMapper, PathMapper pathMapper) {
        super(() -> new DecoderTypModel(), new DecoderTypTranscriber(cvMapper, funktionMapper, pathMapper));
    }
}
