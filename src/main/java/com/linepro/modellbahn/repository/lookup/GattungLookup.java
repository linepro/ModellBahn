package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.repository.GattungRepository;

@Component(PREFIX + "GattungLookup")
public class GattungLookup extends ItemLookup<Gattung, GattungModel> {

    public GattungLookup(GattungRepository repository) {
        super(repository);
    }

}
