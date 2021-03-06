package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.base.ItemRepository;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.service.ItemService;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <E> the element type
 */
public abstract class ItemServiceImpl<M extends ItemModel, E extends Item> implements ItemService<M> {

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

    @Transactional(readOnly = true)
    protected Optional<M> get(Lookup<E> lookup) {
        return lookup.find()
                     .map(e -> entityMutator.convert(e));
    }

    @Transactional
    public M add(M model) {
        E item = modelMutator.convert(model);
        item.setDeleted(false);
        return entityMutator.convert(repository.saveAndFlush(item));
    }

    @Transactional
    protected Optional<M> update(Lookup<E> lookup, M model) {
        return lookup.find()
                     .map(e -> {
                         Boolean deleted = e.getDeleted();
                         E item = modelMutator.apply(model,e);
                         item.setDeleted(deleted);
                         return repository.saveAndFlush(item);
                     })
                     .flatMap(e -> get(lookup)); // Fetch again to populate entity graphs
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
    @Transactional(readOnly = true)
    public Page<M> search(Optional<M> model, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Pageable pageRequest = getPageRequest(pageNumber, pageSize);

        final Page<E> data = findAll(model, pageRequest);

        return getModelPage(data);
    }

    protected Pageable getPageRequest(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        if (!pageNumber.isPresent() && !pageSize.isPresent()) return Pageable.unpaged();

        return PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE));
    }

    protected Page<E> findAll(Optional<M> model, Pageable pageRequest) {
        Example<E> example = Example.of(model.map(m -> modelMutator.convert(m)).orElse(modelMutator.get()));
        final Page<E> data = repository.findAll(example, pageRequest);
        return data;
    }

    protected Page<M> getModelPage(Page<E> data) {
        return data.map(e -> entityMutator.convert(e));
    }
}
