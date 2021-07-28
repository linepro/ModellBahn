package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;
import com.linepro.modellbahn.request.NamedItemRequest;
import com.linepro.modellbahn.service.NamedItemService;

public abstract class NamedItemServiceImpl<M extends NamedItemModel, R extends NamedItemRequest, E extends NamedItem> extends ItemServiceImpl<M, R, E> implements NamedItemService<M,R> {

    protected final NamedItemRepository<E> repository;

    protected NamedItemServiceImpl(NamedItemRepository<E> repository, Mapper<R, E> requestMapper, Mapper<E, M> entityMapper) {
        super(repository, requestMapper, entityMapper);

        this.repository = repository;
    }

    @Override
    public Optional<M> get(String name) {
        return super.get(() -> repository.findByName(name));
    }

    @Override
    public Optional<M> update(String name, R request) {
        return super.update(() -> repository.findByName(name), request);
    }

    @Override
    public boolean delete(String name) {
        return super.delete(() -> repository.findByName(name));
    }
}
