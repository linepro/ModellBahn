package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component(PREFIX + "ProtokollMutator")
public class ProtokollMutator extends MutatorImpl<Protokoll, ProtokollModel> {

    public ProtokollMutator() {
        super(() -> new ProtokollModel(), new NamedTranscriber<Protokoll, ProtokollModel>());
    }

}
