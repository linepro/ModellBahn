package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ZugTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

@Component(PREFIX + "ZugMutator")
public class ZugMutator extends MutatorImpl<Zug,ZugModel> {

    @Autowired
    public ZugMutator(ZugConsistMutator consistMutator) {
        super(() -> new ZugModel(), new ZugTranscriber(consistMutator));
    }
}
