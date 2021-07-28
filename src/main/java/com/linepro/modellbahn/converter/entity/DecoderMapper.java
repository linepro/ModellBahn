package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

@Component(PREFIX + "DecoderMapper")
public class DecoderMapper extends MapperImpl<Decoder, DecoderModel> {

    @Autowired
    public DecoderMapper(ArtikelMapper artikelMapper, DecoderTypMapper decoderTypMapper, DecoderAdressMapper adressMapper, DecoderCvMapper cvMapper, DecoderFunktionMapper funktionMapper) {
        super(() -> new DecoderModel(), new DecoderTranscriber(artikelMapper, decoderTypMapper, adressMapper, cvMapper, funktionMapper));
    }
}
