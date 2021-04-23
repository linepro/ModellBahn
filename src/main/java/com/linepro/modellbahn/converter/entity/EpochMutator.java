package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.EpochTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component(PREFIX + "EpochMutator")
public class EpochMutator extends MutatorImpl<Epoch, EpochModel> {

    public EpochMutator() {
        super(() -> new EpochModel(), new EpochTranscriber());
    }

}
