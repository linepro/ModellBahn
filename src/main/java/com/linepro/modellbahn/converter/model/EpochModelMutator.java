package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.EpochModelTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component(PREFIX + "EpochModelMutator")
public class EpochModelMutator extends MutatorImpl<EpochModel, Epoch> {

    public EpochModelMutator() {
        super(() -> new Epoch(), new EpochModelTranscriber());
    }
}
