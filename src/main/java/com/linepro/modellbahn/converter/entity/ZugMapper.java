package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ZugTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

@Component(PREFIX + "ZugMapper")
public class ZugMapper extends MapperImpl<Zug,ZugModel> {

    @Autowired
    public ZugMapper(ZugConsistMapper consistMapper) {
        super(() -> new ZugModel(), new ZugTranscriber(consistMapper));
    }
}
