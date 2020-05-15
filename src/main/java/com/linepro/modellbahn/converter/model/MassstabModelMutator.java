package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component
public class MassstabModelMutator extends MutatorImpl<MassstabModel, Massstab> {

    public MassstabModelMutator() {
        super(() -> new Massstab(), new NamedTranscriber<MassstabModel, Massstab>());
    }

}
