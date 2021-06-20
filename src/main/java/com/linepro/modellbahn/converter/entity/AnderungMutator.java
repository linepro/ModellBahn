package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.AnderungTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component(PREFIX + "AnderungMutator")
public class AnderungMutator extends MutatorImpl<Anderung, AnderungModel> {

    public AnderungMutator() {
        super(() -> new AnderungModel(), new AnderungTranscriber());
    }
}
