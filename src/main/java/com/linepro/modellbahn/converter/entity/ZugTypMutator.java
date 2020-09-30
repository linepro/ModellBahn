package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;

@Component(PREFIX + "ZugTypMutator")
public class ZugTypMutator extends MutatorImpl<ZugTyp, ZugTypModel> {

    public ZugTypMutator() {
        super(() -> new ZugTypModel(), new NamedTranscriber<ZugTyp, ZugTypModel>());
    }

}
