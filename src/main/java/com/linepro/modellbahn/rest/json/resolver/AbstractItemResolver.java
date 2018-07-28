package com.linepro.modellbahn.rest.json.resolver;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class AbstractItemResolver<E extends IItem> implements ObjectIdResolver {

    protected final IPersister<E> persister;
    
    public AbstractItemResolver(Class<E> entityClass) {
        persister = StaticPersisterFactory.get().createPersister(entityClass);
    }
    
    @Override
    public void bindItem(IdKey id, Object pojo) {
        // We don't do caching.  Not yet.
    }

    @Override
    public Object resolveId(IdKey id) {
        try {
            return persister.findByKey(id.key, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return this;
    }

    @Override
    public boolean canUseFor(ObjectIdResolver resolverType) {
        return resolverType.getClass() == getClass();
    }
}
