package com.linepro.modellbahn.guice;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.ILoggerFactory;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IItemPersister;
import com.linepro.modellbahn.persistence.IItemPersisterFactory;
import com.linepro.modellbahn.persistence.impl.ItemPersister;

public class ItemPersisterFactory implements IItemPersisterFactory {
    
    protected final EntityManager entityManager;

    protected final ILoggerFactory logManager;

    @Inject
    public ItemPersisterFactory(final EntityManager entityManager, final ILoggerFactory logManager) {
        this.entityManager = entityManager;
        this.logManager = logManager;
    }

    public <E extends IItem> IItemPersister<E> create(Class<E> clazz) {
        return new ItemPersister<E>(entityManager, logManager, clazz);
    }
}
