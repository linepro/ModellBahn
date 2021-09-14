package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;
import com.linepro.modellbahn.repository.SpurweiteRepository;

@Component(PREFIX + "SpurweiteLookup")
public class SpurweiteLookup extends ItemLookup<Spurweite, SpurweiteModel> {

    public SpurweiteLookup(SpurweiteRepository repository) {
        super(repository);
    }

}
