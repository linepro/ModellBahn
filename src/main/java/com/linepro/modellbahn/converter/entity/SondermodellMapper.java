package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;

@Component(PREFIX + "SondermodellMapper")
public class SondermodellMapper extends MapperImpl<Sondermodell, SondermodellModel> {

    public SondermodellMapper() {
        super(() -> new SondermodellModel(), new NamedTranscriber<Sondermodell, SondermodellModel>());
    }

}
