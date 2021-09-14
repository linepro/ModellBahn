package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.repository.MotorTypRepository;

@Component(PREFIX + "MotorTypLookup")
public class MotorTypLookup extends ItemLookup<MotorTyp, MotorTypModel> {

    public MotorTypLookup(MotorTypRepository repository) {
        super(repository);
    }

}
