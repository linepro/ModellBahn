package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.NamedItem;
import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.request.NamedItemRequest;
import com.linepro.modellbahn.service.NamedItemService;

public abstract class NamedItemServiceImpl<M extends NamedItemModel, R extends NamedItemRequest, E extends NamedItem> extends ItemServiceImpl<M, R, E> implements NamedItemService<M,R> {

    protected final ItemLookup<E, M> lookup;
    
    protected NamedItemServiceImpl(NamedItemRepository<E> repository, Mapper<R, E> requestMapper, Mapper<E, M> entityMapper, ItemLookup<E, M> lookup) {
        super(repository, requestMapper, entityMapper, lookup);

        this.lookup = lookup;
    }

    @Override
    public Optional<M> get(String name) {
        return lookup.find(name)
                     .map(e -> entityMapper.convert(e));

    }

    @Override
    public Optional<M> update(String name, R request) {
        return lookup.find(name)
                     .map(e -> {
                         Boolean deleted = e.getDeleted();
                         E item = requestMapper.apply(request,e);
                         item.setDeleted(deleted);
                         return repository.saveAndFlush(item);
                     })
                     .flatMap(e -> lookup.find(e)) // Fetch again to populate entity graphs
                     .map(e -> entityMapper.convert(e));
    }

    @Override
    public boolean delete(String name) {
        return lookup.find(name)
                     .map(e -> {
                         repository.delete(e);
                         return true;
                     })
                     .orElse(false);
    }
}
