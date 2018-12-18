package com.linepro.modellbahn.rest.json.resolver;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

class AbstractItemResolver<E extends INamedItem<?>> implements ObjectIdResolver {

    private final IPersister<?> persister;
    
    AbstractItemResolver(Class<E> entityClass) {
        persister = StaticPersisterFactory.get().createPersister(entityClass);
    }
    
    @Override
    public void bindItem(IdKey id, Object pojo) {
        // We don't do caching.  Not yet.
    }

    @Override
    public Object resolveId(IdKey id) {
        try {
            return persister.findByKey(new NameKey(id.key.toString()), false);
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
