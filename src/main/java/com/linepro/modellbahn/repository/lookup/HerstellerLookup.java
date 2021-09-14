package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.HerstellerRepository;

@Component(PREFIX + "HerstellerLookup")
public class HerstellerLookup extends ItemLookup<Hersteller, HerstellerModel> {

    public HerstellerLookup(HerstellerRepository repository) {
        super(repository);
    }

}
