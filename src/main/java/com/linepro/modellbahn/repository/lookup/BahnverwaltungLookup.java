package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;

@Component(PREFIX + "BahnverwaltungLookup")
public class BahnverwaltungLookup extends ItemLookup<Bahnverwaltung, BahnverwaltungModel> {

    public BahnverwaltungLookup(BahnverwaltungRepository repository) {
        super(repository);
    }

}
