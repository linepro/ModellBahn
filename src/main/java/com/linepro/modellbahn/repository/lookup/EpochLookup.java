package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;
import com.linepro.modellbahn.repository.EpochRepository;

@Component(PREFIX + "EpochLookup")
public class EpochLookup extends ItemLookup<Epoch, EpochModel> {

    public EpochLookup(EpochRepository repository) {
        super(repository);
    }

}
