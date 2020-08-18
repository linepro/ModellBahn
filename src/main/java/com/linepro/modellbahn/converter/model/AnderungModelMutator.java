package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.AnderungModelTranscriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component(PREFIX + "AnderungModelMutator")
public class AnderungModelMutator extends MutatorImpl<AnderungModel, Anderung> {

    public AnderungModelMutator() {
        super(() -> new Anderung(), new AnderungModelTranscriber());
    }
}
