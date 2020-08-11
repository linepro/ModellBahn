package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component(PREFIX + "ProtokollModelMutator")
public class ProtokollModelMutator extends MutatorImpl<ProtokollModel, Protokoll> {

    public ProtokollModelMutator() {
        super(() -> new Protokoll(), new NamedModelTranscriber<ProtokollModel, Protokoll>());
    }

}
