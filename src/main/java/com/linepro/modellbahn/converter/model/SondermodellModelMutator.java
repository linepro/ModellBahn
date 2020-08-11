package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;

@Component(PREFIX + "SondermodellModelMutator")
public class SondermodellModelMutator extends MutatorImpl<SondermodellModel, Sondermodell> {

    public SondermodellModelMutator() {
        super(() -> new Sondermodell(), new NamedModelTranscriber<SondermodellModel, Sondermodell>());
    }

}
