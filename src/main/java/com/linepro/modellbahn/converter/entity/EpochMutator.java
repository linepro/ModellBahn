package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component("EpochMutator")
public class EpochMutator extends MutatorImpl<Epoch, EpochModel> {

    public EpochMutator() {
        super(() -> new EpochModel(), new NamedTranscriber<Epoch, EpochModel>());
    }

}
