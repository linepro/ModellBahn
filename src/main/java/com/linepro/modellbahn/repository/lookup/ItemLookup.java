package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Component("ItemLookup")
public class ItemLookup {
    
    public <E extends NamedItem> E find(String name, NamedItemRepository<E> repository) {
        return Optional.ofNullable(name)
                       .flatMap(m -> repository.findByName(name))
                       .orElse(null);
    }
}
