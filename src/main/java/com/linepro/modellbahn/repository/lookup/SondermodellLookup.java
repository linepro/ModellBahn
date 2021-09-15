package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;
import com.linepro.modellbahn.repository.SondermodellRepository;

@Component(PREFIX + "SondermodellLookup")
public class SondermodellLookup extends ItemLookup<Sondermodell, SondermodellModel> {

    public SondermodellLookup(SondermodellRepository repository) {
        super(repository);
    }

}
