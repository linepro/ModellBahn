package com.linepro.modellbahn.persistence.impl;

import java.util.Collection;

import com.linepro.modellbahn.persistence.IPersister;

public class LazyCollectionPopulator implements ILazyCollectionPopulator {

    protected final IPersister<?> persister;
    
    public LazyCollectionPopulator(IPersister<?> persister) {
        this.persister = persister;
    }
    
    @Override
    public void populate(Collection<?> collection) throws Exception {
        persister.begin();
        
        collection.size();
        
        persister.commit();
    }
}
