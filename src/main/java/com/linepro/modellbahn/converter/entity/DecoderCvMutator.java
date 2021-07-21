package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderCvTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

@Component(PREFIX + "DecoderCvMutator")
public class DecoderCvMutator extends MapperImpl<DecoderCv, DecoderCvModel> {

    public DecoderCvMutator() {
        super(() -> new DecoderCvModel(), new DecoderCvTranscriber());
    }
}
