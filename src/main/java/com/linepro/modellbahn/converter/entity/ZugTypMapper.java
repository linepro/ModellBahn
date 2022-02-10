package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;

@Component(PREFIX + "ZugTypMapper")
public class ZugTypMapper extends MapperImpl<ZugTyp, ZugTypModel> {

    @SuppressWarnings("unchecked")
    public ZugTypMapper(@Qualifier(PREFIX + "NamedTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new ZugTypModel(), (Transcriber<ZugTyp, ZugTypModel>) transcriber);
    }

}
