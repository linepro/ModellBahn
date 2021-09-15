package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;

@Component(PREFIX + "AntriebLookup")
public class AntriebLookup extends ItemLookup<Antrieb, AntriebModel> {

    public AntriebLookup(AntriebRepository repository) {
        super(repository);
    }

}
