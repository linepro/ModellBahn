package com.linepro.modellbahn.service;

import java.util.Optional;

import com.linepro.modellbahn.model.NamedItemModel;

public interface NamedItemService<M extends NamedItemModel> extends ItemService<M> {

    Optional<M> get(String name);

    Optional<M> update(String name, M model);

    boolean delete(String name);
}
