package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.repository.AufbauRepository;

@Component(PREFIX + "AufbauLookup")
public class AufbauLookup extends ItemLookup<Aufbau, AufbauModel> {

    public AufbauLookup(AufbauRepository repository) {
        super(repository);
    }

}
