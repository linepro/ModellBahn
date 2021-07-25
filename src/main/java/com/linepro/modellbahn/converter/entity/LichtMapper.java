package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component(PREFIX + "LichtMapper")
public class LichtMapper extends MapperImpl<Licht, LichtModel> {

    public LichtMapper(PathMapper pathMapper) {
        super(() -> new LichtModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }

}
