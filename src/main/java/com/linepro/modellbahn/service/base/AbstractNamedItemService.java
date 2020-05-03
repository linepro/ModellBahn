package com.linepro.modellbahn.service.base;

import java.util.Optional;

import com.linepro.modellbahn.entity.base.NamedItem;
import com.linepro.modellbahn.model.base.NamedItemModel;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

public abstract class AbstractNamedItemService<M extends NamedItemModel, E extends NamedItem>
        extends AbstractItemService<M, E> {

    protected final NamedItemRepository<E> persister;

    protected AbstractNamedItemService(NamedItemRepository<E> persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<E> findByModel(M model) {
        return persister.findByName(model.getName());
    }
}
