package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.AnderungTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component(PREFIX + "AnderungMapper")
public class AnderungMapper extends MapperImpl<Anderung, AnderungModel> {

    public AnderungMapper() {
        super(() -> new AnderungModel(), new AnderungTranscriber());
    }
}
