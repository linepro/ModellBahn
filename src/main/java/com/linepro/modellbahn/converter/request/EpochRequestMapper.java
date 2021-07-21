package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.EpochRequestTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.request.EpochRequest;

@Component(PREFIX + "EpochRequestMapper")
public class EpochRequestMapper extends MapperImpl<EpochRequest, Epoch> {

    public EpochRequestMapper() {
        super(() -> new Epoch(), new EpochRequestTranscriber());
    }
}
