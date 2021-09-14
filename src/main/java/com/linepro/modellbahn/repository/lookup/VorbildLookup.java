package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;

@Component(PREFIX + "VorbildLookup")
public class VorbildLookup extends ItemLookup<Vorbild, VorbildModel> {

    public VorbildLookup(VorbildRepository repository) {
        super(repository);
    }

}
