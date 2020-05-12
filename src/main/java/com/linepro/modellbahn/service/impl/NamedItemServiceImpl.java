package com.linepro.modellbahn.service.impl;

import java.util.Optional;
import java.util.function.Supplier;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;
import com.linepro.modellbahn.service.NamedItemService;

public abstract class NamedItemServiceImpl<M extends NamedItemModel, E extends NamedItem> extends ItemServiceImpl<M, E> implements NamedItemService<M> {

    protected final NamedItemRepository<E> repository;
    
    public NamedItemServiceImpl(NamedItemRepository<E> repository, Supplier<M> modelSupplier, Supplier<E> entitySupplier) {
        this(repository, new MutatorImpl<M, E>(entitySupplier, new NamedTranscriber<M, E>()),
                        new MutatorImpl<E, M>(modelSupplier, new NamedTranscriber<E, M>()));
    }

    protected NamedItemServiceImpl(NamedItemRepository<E> repository, Mutator<M, E> modelMutator, Mutator<E, M> entityMutator) {
        super(repository, modelMutator, entityMutator);
        
        this.repository = repository;
    }

    @Override
    public Optional<M> get(String name) {
        return super.get(() -> repository.findByName(name));
    }

    @Override
    public Optional<M> update(String name, M model) {
        return super.update(() -> repository.findByName(name), model);
    }

    @Override
    public boolean delete(String name) {
        return super.delete(() -> repository.findByName(name));
    }
}
