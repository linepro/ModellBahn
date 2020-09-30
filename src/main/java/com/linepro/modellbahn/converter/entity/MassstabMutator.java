package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;

@Component(PREFIX + "MassstabMutator")
public class MassstabMutator extends MutatorImpl<Massstab, MassstabModel> {

    public MassstabMutator() {
        super(() -> new MassstabModel(), new NamedTranscriber<Massstab, MassstabModel>());
    }

}
