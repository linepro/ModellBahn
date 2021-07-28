package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.request.KategorieRequest;

@Component(PREFIX + "KategorieRequestMapper")
public class KategorieRequestMapper extends MapperImpl<KategorieRequest, Kategorie> {

    public KategorieRequestMapper() {
        super(() -> new Kategorie(), new NamedRequestTranscriber<KategorieRequest, Kategorie>());
    }
}
