package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.VorbildRequestTranscriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.request.VorbildRequest;

@Component(PREFIX + "VorbildRequestMapper")
public class VorbildRequestMapper extends MapperImpl<VorbildRequest, Vorbild> {

    @Autowired
    public VorbildRequestMapper(VorbildRequestTranscriber transcriber) {
        super(() -> new Vorbild(), transcriber);
    }
}
