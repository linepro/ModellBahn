package com.linepro.modellbahn.persistence.impl;

import java.util.Collection;

import com.linepro.modellbahn.persistence.ILazyCollectionPopulator;
import com.linepro.modellbahn.persistence.ISessionManager;

public class LazyCollectionPopulator implements ILazyCollectionPopulator {

    protected final ISessionManager persister;
    
    public LazyCollectionPopulator(ISessionManager persister) {
        this.persister = persister;
    }
    
    @Override
    public void populate(Collection<?> collection) throws Exception {
        persister.begin();
        
        collection.size();
        
        persister.commit();
    }
}
