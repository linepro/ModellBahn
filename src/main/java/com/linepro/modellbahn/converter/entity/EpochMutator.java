package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.EpochTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component(PREFIX + "EpochMutator")
public class EpochMutator extends MapperImpl<Epoch, EpochModel> {

    public EpochMutator(PathMutator pathMutator) {
        super(() -> new EpochModel(), new EpochTranscriber(pathMutator));
    }

}
