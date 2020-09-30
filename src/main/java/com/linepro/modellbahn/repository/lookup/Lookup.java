package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import com.linepro.modellbahn.entity.Item;

public interface Lookup<E extends Item> {
    Optional<E> find();
}
