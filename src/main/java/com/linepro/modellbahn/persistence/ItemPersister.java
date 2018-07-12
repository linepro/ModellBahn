package com.linepro.modellbahn.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.ILoggerFactory;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.model.IItem;

public class ItemPersister<E extends IItem> extends AbstractPersister<E, Long> implements IItemPersister<E> {

    @Inject
    public ItemPersister(final EntityManager entityManager, final ILoggerFactory logManager, @Assisted final Class<E> clazz) {
        super(entityManager, logManager, clazz);
    }

    @Override
    public E update(E entity) {
        return update(entity, entity.getId());
    }

    @Override
    public void delete(Long id) {
        super.deleteById(id);
    }
}