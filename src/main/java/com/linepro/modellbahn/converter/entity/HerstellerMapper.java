package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.HerstellerTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component(PREFIX + "HerstellerMapper")
public class HerstellerMapper extends MapperImpl<Hersteller, HerstellerModel> {

    public HerstellerMapper(HerstellerTranscriber transcriber) {
        super(() -> new HerstellerModel(), transcriber);
    }
}
