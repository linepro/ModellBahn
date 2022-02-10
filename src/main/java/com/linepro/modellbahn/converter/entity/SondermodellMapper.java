package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;

@Component(PREFIX + "SondermodellMapper")
public class SondermodellMapper extends MapperImpl<Sondermodell, SondermodellModel> {

    @SuppressWarnings("unchecked")
    public SondermodellMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new SondermodellModel(), (Transcriber<Sondermodell, SondermodellModel>) transcriber);
    }

}
