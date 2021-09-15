package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;

@Component(PREFIX + "KategorieLookup")
public class KategorieLookup extends ItemLookup<Kategorie, KategorieModel> {

    public KategorieLookup(KategorieRepository repository) {
        super(repository);
    }

}
