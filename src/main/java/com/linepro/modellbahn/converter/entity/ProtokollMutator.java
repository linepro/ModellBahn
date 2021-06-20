package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;

@Component(PREFIX + "ProtokollMutator")
public class ProtokollMutator extends MutatorImpl<Protokoll, ProtokollModel> {

    public ProtokollMutator(PathMutator pathMutator) {
        super(() -> new ProtokollModel(), new NamedAbbildungTranscriber<Protokoll, ProtokollModel>(pathMutator));
    }

}
