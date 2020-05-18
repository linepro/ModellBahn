package com.linepro.modellbahn.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.base.ItemRepository;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.service.ItemService;

import lombok.SneakyThrows;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <E> the element type
 */
public abstract class ItemServiceImpl<M extends ItemModel, E extends Item> implements ItemService<M> {

    protected static final List<String> PAGE_FIELDS = Arrays.asList(ApiNames.PAGE_NUMBER, ApiNames.PAGE_SIZE);

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;


    /** The repository. */
    protected final ItemRepository<E> repository;
    
    protected final Mutator<M,E> modelMutator;
    
    protected final Mutator<E,M> entityMutator;
    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    protected ItemServiceImpl(ItemRepository<E> repository, Mutator<M,E> modelMutator, Mutator<E,M> entityMutator) {
        this.repository = repository;
        this.modelMutator = modelMutator;
        this.entityMutator = entityMutator;
    }

    @Transactional
    protected Optional<M> get(Lookup<E> lookup) {
        return lookup.find()
                     .map(e -> entityMutator.convert(e, 1));
    }

    @Transactional
    public M add(M model) {
        return entityMutator.convert(repository.saveAndFlush(modelMutator.convert(model)),1);
    }

    @Transactional
    protected Optional<M> update(Lookup<E> lookup, M model) {
        return lookup.find()
                     .map(e -> entityMutator.convert(repository.saveAndFlush(modelMutator.apply(model,e))));
    }

    @Transactional
    public boolean delete(Lookup<E> lookup) {
        return lookup.find()
                     .map(e -> {
                         repository.delete(e);
                         return true;
                         })
                     .orElse(false);
    }

    @Override
    @Transactional
    @SneakyThrows
    public Page<M> search(Optional<M> model, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Example<E> example = Example.of(model.map(m -> modelMutator.convert(m)).orElse(modelMutator.get()));
        PageRequest pageRequest = PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE));
        
        final Page<E> data = repository.findAll(example, pageRequest);
        final Page<M> page = data.map(e -> entityMutator.convert(e));
        return page;
    }
}
