package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component
public class AntriebModelMutator extends MutatorImpl<AntriebModel, Antrieb> {

    public AntriebModelMutator() {
        super(() -> new Antrieb(), new NamedTranscriber<AntriebModel, Antrieb>());
    }
}
