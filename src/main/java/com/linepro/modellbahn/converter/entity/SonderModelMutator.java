package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.SonderModel;
import com.linepro.modellbahn.model.SonderModelModel;

@Component
public class SonderModelMutator extends MutatorImpl<SonderModel, SonderModelModel> {

    public SonderModelMutator() {
        super(() -> new SonderModelModel(), new NamedTranscriber<SonderModel, SonderModelModel>());
    }

}
