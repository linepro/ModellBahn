package com.linepro.modellbahn.guice;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.ILoggerFactory;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IItemPersister;
import com.linepro.modellbahn.persistence.impl.ItemPersister;

public class StaticPersisterFactory {

    @Inject
    protected static EntityManager entityManager;

    @Inject
    protected static ILoggerFactory logManager;

    public static  <E extends IItem> IItemPersister<E> create(Class<E> clazz) {
        return new ItemPersister<E>(entityManager, logManager, clazz);
    }
}
