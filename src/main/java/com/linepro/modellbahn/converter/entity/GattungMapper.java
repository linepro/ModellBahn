package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;

@Component(PREFIX + "GattungMapper")
public class GattungMapper extends MapperImpl<Gattung, GattungModel> {

    public GattungMapper() {
        super(() -> new GattungModel(), new NamedTranscriber<Gattung, GattungModel>());
    }

}
