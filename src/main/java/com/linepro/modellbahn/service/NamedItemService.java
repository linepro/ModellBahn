package com.linepro.modellbahn.service;

import java.util.Optional;

import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.request.NamedItemRequest;

public interface NamedItemService<M extends NamedItemModel,R extends NamedItemRequest> extends ItemService<M,R> {

    Optional<M> get(String name);

    Optional<M> update(String name, R request);

    boolean delete(String name);
}
