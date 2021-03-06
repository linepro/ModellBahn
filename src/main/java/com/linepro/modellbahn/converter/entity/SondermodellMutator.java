package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;

@Component(PREFIX + "SondermodellMutator")
public class SondermodellMutator extends MutatorImpl<Sondermodell, SondermodellModel> {

    public SondermodellMutator() {
        super(() -> new SondermodellModel(), new NamedTranscriber<Sondermodell, SondermodellModel>());
    }

}
