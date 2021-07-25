package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component(PREFIX + "SpurweiteMapper")
public class SpurweiteMapper extends MapperImpl<Spurweite, SpurweiteModel> {

    public SpurweiteMapper() {
        super(() -> new SpurweiteModel(), new NamedTranscriber<Spurweite, SpurweiteModel>());
    }

}
