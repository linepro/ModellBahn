package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;

@Component
public class AntriebMutator extends MutatorImpl<Antrieb, AntriebModel> {

    public AntriebMutator() {
        super(() -> new AntriebModel(), new NamedTranscriber<Antrieb, AntriebModel>());
    }

}
