package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.SonderModel;
import com.linepro.modellbahn.model.SonderModelModel;

@Component
public class SonderModelModelMutator extends MutatorImpl<SonderModelModel, SonderModel> {

    public SonderModelModelMutator() {
        super(() -> new SonderModel(), new NamedTranscriber<SonderModelModel, SonderModel>());
    }

}
