package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.entity.transcriber.BahnverwaltungTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component(PREFIX + "BahnverwaltungMapper")
public class BahnverwaltungMapper extends MapperImpl<Bahnverwaltung, BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungMapper(PathMapper pathMapper) {
        super(() -> new BahnverwaltungModel(), new BahnverwaltungTranscriber(pathMapper));
    }

    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }

}
