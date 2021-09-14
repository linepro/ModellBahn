package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.repository.MassstabRepository;

@Component(PREFIX + "MassstabLookup")
public class MassstabLookup extends ItemLookup<Massstab, MassstabModel> {

    public MassstabLookup(MassstabRepository repository) {
        super(repository);
    }

}
