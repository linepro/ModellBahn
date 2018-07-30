package com.linepro.modellbahn.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.ILoggerFactory;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.IPersisterFactory;

/**
 * A factory for creating Persister objects.
 */
public class PersisterFactory implements IPersisterFactory {

    /** The entity manager. */
    protected final EntityManager entityManager;

    /** The log manager. */
    protected final ILoggerFactory logManager;

    /** The persisters. */
    protected final Map<Class<? extends IItem<?>>,IPersister<? extends IItem<?>>> persisters = new HashMap<>();
    
    /**
     * Instantiates a new persister factory.
     *
     * @param entityManager the entity manager
     * @param logManager the log manager
     */
    @Inject
    public PersisterFactory(final EntityManager entityManager, final ILoggerFactory logManager) {
        this.entityManager = entityManager;
        this.logManager = logManager;
    }

    @Override
    public synchronized <E extends IItem<?>> IPersister<E> createPersister(Class<E> entityClass) {
        @SuppressWarnings("unchecked")
        IPersister<E> persister = (IPersister<E>) persisters.get(entityClass);
        
        if (persister == null) {
            persister = new ItemPersister<E>(entityManager, logManager, entityClass);
            
            registerConverter(persister, entityClass);

            persisters.put(entityClass, persister);
        }
        
        return persister;
    }
    
    void registerConverter(IPersister<?> persister, Class<?> entityClass) {
        if (INamedItem.class.isAssignableFrom(entityClass)) {
            ConvertUtils.register(new NamedItemConverter(persister), entityClass);
        }
    }
}
