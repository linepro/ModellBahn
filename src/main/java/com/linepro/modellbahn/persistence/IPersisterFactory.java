package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;

public interface IPersisterFactory {
    <E extends IItem> IPersister<E> createItemPersister(Class<E> clazz);

    <E extends INamedItem> IPersister<E> createNamedItemPersister(Class<E> clazz);
}
