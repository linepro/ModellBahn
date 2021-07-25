package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component(PREFIX + "AntriebMapper")
public class AntriebMapper extends MapperImpl<Antrieb, AntriebModel> {

    public AntriebMapper(PathMapper pathMapper) {
        super(() -> new AntriebModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }

}
