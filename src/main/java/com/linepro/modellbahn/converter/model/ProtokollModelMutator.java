package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component
public class ProtokollModelMutator extends MutatorImpl<ProtokollModel, Protokoll> {

    public ProtokollModelMutator() {
        super(() -> new Protokoll(), new NamedTranscriber<ProtokollModel, Protokoll>());
    }

}
