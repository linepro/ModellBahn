package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component(PREFIX + "KupplungMapper")
public class KupplungMapper extends MapperImpl<Kupplung, KupplungModel> {

    @Autowired
    public KupplungMapper(PathMapper pathMapper) {
        super(() -> new KupplungModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }
}
