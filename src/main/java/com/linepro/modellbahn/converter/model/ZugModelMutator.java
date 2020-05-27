package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component
public class ZugModelMutator extends MutatorImpl<ZugModel, Zug> {

    private final ZugTypRepository repository;

    private final ItemLookup lookup;

    @Autowired
    public ZugModelMutator(ZugTypRepository repository, ItemLookup lookup) {
        super(() -> new Zug(), new NamedTranscriber<>());
        this.repository = repository;
        this.lookup = lookup;
    }

    @Override
    public Zug apply(ZugModel source, Zug destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setName(source.getName());
        }

        return applyFields(source, destination);
    }

    @Override
    public Zug applyFields(ZugModel source, Zug destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setBezeichnung(source.getBezeichnung());
            destination.setZugTyp(lookup.find(source.getZugTyp(), repository));
        }
        
        return destination;
    }
}
