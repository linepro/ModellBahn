package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component(PREFIX + "MassstabMapper")
public class MassstabMapper extends MapperImpl<Massstab, MassstabModel> {

    public MassstabMapper() {
        super(() -> new MassstabModel(), new NamedTranscriber<Massstab, MassstabModel>());
    }

}
