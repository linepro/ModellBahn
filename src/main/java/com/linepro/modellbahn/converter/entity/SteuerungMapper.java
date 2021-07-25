package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component(PREFIX + "SteuerungMapper")
public class SteuerungMapper extends MapperImpl<Steuerung, SteuerungModel> {

    public SteuerungMapper(PathMapper pathMapper) {
        super(() -> new SteuerungModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }

}
