package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ZugConsistTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

@Component(PREFIX + "ZugConsistMapper")
public class ZugConsistMapper extends MapperImpl<ZugConsist, ZugConsistModel> {

    @Autowired
    public ZugConsistMapper(ArtikelMapper artikelMapper) {
        super(() -> new ZugConsistModel(), new ZugConsistTranscriber(artikelMapper));
    }
}
