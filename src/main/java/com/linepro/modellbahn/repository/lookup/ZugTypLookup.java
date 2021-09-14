package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;
import com.linepro.modellbahn.repository.ZugTypRepository;

@Component(PREFIX + "ZugTypLookup")
public class ZugTypLookup extends ItemLookup<ZugTyp, ZugTypModel> {

    public ZugTypLookup(ZugTypRepository repository) {
        super(repository);
    }

}
