package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.HerstellerRequestTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.request.HerstellerRequest;

@Component(PREFIX + "HerstellerRequestMapper")
public class HerstellerRequestMapper extends MapperImpl<HerstellerRequest, Hersteller> {

    public HerstellerRequestMapper() {
        super(() -> new Hersteller(), new HerstellerRequestTranscriber());
    }

}
