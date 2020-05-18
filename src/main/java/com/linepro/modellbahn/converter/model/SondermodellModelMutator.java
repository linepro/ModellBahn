package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;

@Component
public class SondermodellModelMutator extends MutatorImpl<SondermodellModel, Sondermodell> {

    public SondermodellModelMutator() {
        super(() -> new Sondermodell(), new NamedTranscriber<SondermodellModel, Sondermodell>());
    }

}
