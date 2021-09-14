package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;
import com.linepro.modellbahn.repository.KupplungRepository;

@Component(PREFIX + "KupplungLookup")
public class KupplungLookup extends ItemLookup<Kupplung, KupplungModel> {

    public KupplungLookup(KupplungRepository repository) {
        super(repository);
    }

}
