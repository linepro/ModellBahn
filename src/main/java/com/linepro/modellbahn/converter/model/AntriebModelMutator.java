package com.linepro.modellbahn.converter.model;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component
public class AntriebModelMutator extends MutatorImpl<AntriebModel, Antrieb> {

    public AntriebModelMutator(Supplier<Antrieb> supplier, Transcriber<AntriebModel, Antrieb> transcriber) {
        super(() -> new Antrieb(), new NamedTranscriber<AntriebModel, Antrieb>());
    }
}
