package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.base.ItemRepository;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.ItemRequest;
import com.linepro.modellbahn.service.ItemService;
import com.linepro.modellbahn.service.criterion.Criterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <E> the element type
 */
public abstract class ItemServiceImpl<M extends ItemModel, R extends ItemRequest, E extends Item> implements ItemService<M,R> {

    /** The repository. */
    protected final ItemRepository<E> repository;

    protected final Mapper<R,E> requestMapper;

    protected final Mapper<E,M> entityMapper;
    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    protected ItemServiceImpl(ItemRepository<E> repository, Mapper<R,E> requestMapper, Mapper<E,M> entityMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.entityMapper = entityMapper;
    }

    @Transactional(readOnly = true)
    protected Optional<M> get(Lookup<E> lookup) {
        return lookup.find()
                     .map(e -> entityMapper.convert(e));
    }

    @Transactional
    public M add(R request) {
        E item = requestMapper.convert(request);
        item.setDeleted(false);
        return entityMapper.convert(repository.saveAndFlush(item));
    }

    @Transactional
    protected Optional<M> update(Lookup<E> lookup, R request) {
        return lookup.find()
                     .map(e -> {
                         Boolean deleted = e.getDeleted();
                         E item = requestMapper.apply(request,e);
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
    public Page<M> search(Criterion criterion, PageCriteria page) {
        final Page<E> data = repository.findAll(criterion, page.getPageRequest());

        return getModelPage(data);
    }

    protected Page<M> getModelPage(Page<E> data) {
        return data.map(e -> entityMapper.convert(e));
    }
}
