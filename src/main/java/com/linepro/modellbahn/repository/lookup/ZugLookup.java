package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugRepository;

@Component(PREFIX + "ZugLookup")
public class ZugLookup extends ItemLookup<Zug, ZugModel> {

    public ZugLookup(ZugRepository repository) {
        super(repository);
    }

}
