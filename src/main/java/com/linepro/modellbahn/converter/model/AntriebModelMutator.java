package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component(PREFIX + "AntriebModelMutator")
public class AntriebModelMutator extends MutatorImpl<AntriebModel, Antrieb> {

    public AntriebModelMutator() {
        super(() -> new Antrieb(), new NamedModelTranscriber<AntriebModel, Antrieb>());
    }
}
