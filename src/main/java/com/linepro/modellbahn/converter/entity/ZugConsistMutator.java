package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ZugConsistTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

@Component(PREFIX + "ZugConsistMutator")
public class ZugConsistMutator extends MutatorImpl<ZugConsist, ZugConsistModel> {

    @Autowired
    public ZugConsistMutator(ArtikelMutator artikelMutator) {
        super(() -> new ZugConsistModel(), new ZugConsistTranscriber(artikelMutator));
    }
}
