package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Component
public class ItemLookup {
    
    public <E extends NamedItem, M extends NamedItemModel> E find(M model, NamedItemRepository<E> repository) {
        return Optional.ofNullable(model)
                       .flatMap(m -> repository.findByName(m.getName()))
                       .orElse(null);
    }
}
