package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;
import com.linepro.modellbahn.repository.ProtokollRepository;

@Component(PREFIX + "ProtokollLookup")
public class ProtokollLookup extends ItemLookup<Protokoll, ProtokollModel> {

    public ProtokollLookup(ProtokollRepository repository) {
        super(repository);
    }

}
