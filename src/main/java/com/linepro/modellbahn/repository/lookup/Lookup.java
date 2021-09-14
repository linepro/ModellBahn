package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;

public interface Lookup<E extends Item, M extends ItemModel> {
    Optional<E> find(E item);

    Optional<E> find(M model);
}
