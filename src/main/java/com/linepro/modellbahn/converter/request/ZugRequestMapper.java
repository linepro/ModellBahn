package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ZugRequestTranscriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.request.ZugRequest;

@Component(PREFIX + "ZugRequestMapper")
public class ZugRequestMapper extends MapperImpl<ZugRequest, Zug> {

    @Autowired
    public ZugRequestMapper(ZugRequestTranscriber transcriber) {
        super(() -> new Zug(), transcriber);
    }
}
