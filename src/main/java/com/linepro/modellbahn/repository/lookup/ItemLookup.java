package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ItemLookup<E extends NamedItem, M extends NamedItemModel> implements Lookup<E,M> {

    private final NamedItemRepository<E> repository;

    public Optional<E> find(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<E> find(M model) {
        if (model != null && model.getName() != null) {
            return find(model.getName());
        }

        return Optional.empty();
    }

    @Override
    public Optional<E> find(E item) {
        if (item != null && item.getName() != null) {
            return find(item.getName());
        }

        return Optional.empty();
    }
}
