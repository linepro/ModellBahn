package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;
import com.linepro.modellbahn.repository.LichtRepository;

@Component(PREFIX + "LichtLookup")
public class LichtLookup extends ItemLookup<Licht, LichtModel> {

    public LichtLookup(LichtRepository repository) {
        super(repository);
    }

}
