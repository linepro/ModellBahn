package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.repository.SteuerungRepository;

@Component(PREFIX + "SteuerungLookup")
public class SteuerungLookup extends ItemLookup<Steuerung, SteuerungModel> {

    public SteuerungLookup(SteuerungRepository repository) {
        super(repository);
    }

}
