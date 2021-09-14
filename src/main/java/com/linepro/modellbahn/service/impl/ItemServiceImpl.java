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

    protected final Lookup<E, M> lookup;

    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    protected ItemServiceImpl(ItemRepository<E> repository, Mapper<R,E> requestMapper, Mapper<E,M> entityMapper, Lookup<E,M> lookup) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.entityMapper = entityMapper;
        this.lookup = lookup;
    }

    @Transactional(readOnly = true)
    protected Optional<M> get(M model) {
        return lookup.find(model)
                     .map(e -> entityMapper.convert(e));
    }

    @Transactional
    public M add(R request) {
        E item = requestMapper.convert(request);
        item.setDeleted(false);
        return entityMapper.convert(repository.saveAndFlush(item));
    }

    @Transactional
    protected Optional<M> update(M model, R request) {
        return lookup.find(model)
                     .map(e -> {
                         Boolean deleted = e.getDeleted();
                         E item = requestMapper.apply(request,e);
                         item.setDeleted(deleted);
                         return repository.saveAndFlush(item);
                     })
                     .flatMap(e -> lookup.find(e)) // Fetch again to populate entity graphs
                     .map(e -> entityMapper.convert(e));
    }

    @Transactional
    public boolean delete(M model) {
        return lookup.find(model)
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
