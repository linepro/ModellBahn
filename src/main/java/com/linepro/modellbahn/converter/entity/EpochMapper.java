package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.EpochTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component(PREFIX + "EpochMapper")
public class EpochMapper extends MapperImpl<Epoch, EpochModel> {

    public EpochMapper(EpochTranscriber transcriber) {
        super(() -> new EpochModel(), transcriber);
    }

}
