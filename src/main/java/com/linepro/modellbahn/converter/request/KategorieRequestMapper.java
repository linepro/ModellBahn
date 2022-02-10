package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.request.KategorieRequest;

@Component(PREFIX + "KategorieRequestMapper")
public class KategorieRequestMapper extends MapperImpl<KategorieRequest, Kategorie> {

    @Autowired
    @SuppressWarnings("unchecked")
    public KategorieRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Kategorie(), (Transcriber<KategorieRequest, Kategorie>) transcriber);
    }
}
