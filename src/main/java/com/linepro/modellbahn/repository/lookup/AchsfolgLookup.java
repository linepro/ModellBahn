package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;

@Component(PREFIX + "AchsfolgLookup")
public class AchsfolgLookup extends ItemLookup<Achsfolg, AchsfolgModel> {

    public AchsfolgLookup(AchsfolgRepository repository) {
        super(repository);
    }

}
