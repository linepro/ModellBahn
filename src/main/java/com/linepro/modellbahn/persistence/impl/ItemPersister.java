package com.linepro.modellbahn.persistence.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.ILoggerFactory;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IItemPersister;

public class ItemPersister<E extends IItem> extends Persister<Long, E> implements IItemPersister<E> {

    @Inject
    public ItemPersister(final EntityManager entityManager, final ILoggerFactory logManager, @Assisted final Class<E> clazz) {
        super(entityManager, logManager, clazz);
    }

    @Override
    public E update(E entity) {
        return update(entity.getId(), entity);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}