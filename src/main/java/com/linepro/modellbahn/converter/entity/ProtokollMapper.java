package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component(PREFIX + "ProtokollMapper")
public class ProtokollMapper extends MapperImpl<Protokoll, ProtokollModel> {

    public ProtokollMapper(PathMapper pathMapper) {
        super(() -> new ProtokollModel(), new NamedAbbildungTranscriber<Protokoll, ProtokollModel>(pathMapper));
    }

}
