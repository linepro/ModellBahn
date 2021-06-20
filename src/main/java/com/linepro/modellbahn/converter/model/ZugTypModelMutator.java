package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;

@Component(PREFIX + "ZugTypModelMutator")
public class ZugTypModelMutator extends MutatorImpl<ZugTypModel, ZugTyp> {

    public ZugTypModelMutator() {
        super(() -> new ZugTyp(), new NamedModelTranscriber<>());
    }
}
