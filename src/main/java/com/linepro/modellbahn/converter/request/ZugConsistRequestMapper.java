package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ZugConsistRequestTranscriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.repository.lookup.ZugLookup;
import com.linepro.modellbahn.request.ZugConsistRequest;

@Component(PREFIX + "ZugConsistRequestMapper")
public class ZugConsistRequestMapper extends MapperImpl<ZugConsistRequest, ZugConsist> {

    @Autowired
    public ZugConsistRequestMapper(ZugLookup zugLookup, ArtikelLookup artikelLookup) {
        super(() -> new ZugConsist(), new ZugConsistRequestTranscriber(zugLookup, artikelLookup));
    }
}
