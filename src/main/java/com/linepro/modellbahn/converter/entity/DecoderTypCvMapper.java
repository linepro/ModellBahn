package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypCvTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component(PREFIX + "DecoderTypCvMapper")
public class DecoderTypCvMapper extends MapperImpl<DecoderTypCv,DecoderTypCvModel> {

    public DecoderTypCvMapper() {
        super(() -> new DecoderTypCvModel(), new DecoderTypCvTranscriber());
    }
}
