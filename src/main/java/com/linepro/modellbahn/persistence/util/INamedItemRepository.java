package com.linepro.modellbahn.persistence.util;

import com.linepro.modellbahn.model.util.AbstractNamedItem;

@SuppressWarnings("rawtypes")
public interface INamedItemRepository<T extends AbstractNamedItem> extends IItemRepository<T> {
    
    T findByName(String name);

    void deleteByName(String name);
}
