package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component("MassstabMutator")
public class MassstabMutator extends MutatorImpl<Massstab, MassstabModel> {

    public MassstabMutator() {
        super(() -> new MassstabModel(), new NamedTranscriber<Massstab, MassstabModel>());
    }

}
