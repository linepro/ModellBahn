package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;

@Component(PREFIX + "AufbauMapper")
public class AufbauMapper extends MapperImpl<Aufbau, AufbauModel> {

    @Autowired
    public AufbauMapper(PathMapper pathMapper) {
        super(() -> new AufbauModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }
}
