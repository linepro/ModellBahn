package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.UnterKategorieRequestTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.request.UnterKategorieRequest;

@Component(PREFIX + "UnterKategorieRequestMapper")
public class UnterKategorieRequestMapper extends MapperImpl<UnterKategorieRequest, UnterKategorie> {

    @Autowired
    public UnterKategorieRequestMapper(UnterKategorieRequestTranscriber transcriber) {
        super(() -> new UnterKategorie(), transcriber);
    }

}
