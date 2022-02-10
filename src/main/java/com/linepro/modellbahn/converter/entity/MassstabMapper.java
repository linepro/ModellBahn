package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component(PREFIX + "MassstabMapper")
public class MassstabMapper extends MapperImpl<Massstab, MassstabModel> {

    @SuppressWarnings("unchecked")
    public MassstabMapper(@Qualifier(PREFIX + "NamedTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new MassstabModel(), (Transcriber<Massstab, MassstabModel>) transcriber);
    }

}
