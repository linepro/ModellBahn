package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.VorbildTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

@Component(PREFIX + "VorbildMapper")
public class VorbildMapper extends MapperImpl<Vorbild,VorbildModel> {

    @Autowired
    public VorbildMapper(VorbildTranscriber transcriber) {
        super(() -> new VorbildModel(), transcriber);
    }
}
