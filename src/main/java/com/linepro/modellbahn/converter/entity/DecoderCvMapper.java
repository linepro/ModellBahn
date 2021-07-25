package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderCvTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

@Component(PREFIX + "DecoderCvMapper")
public class DecoderCvMapper extends MapperImpl<DecoderCv, DecoderCvModel> {

    public DecoderCvMapper() {
        super(() -> new DecoderCvModel(), new DecoderCvTranscriber());
    }
}
