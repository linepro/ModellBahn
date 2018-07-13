package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

public interface IItemPersisterFactory {
    <E extends IItem> IItemPersister<E> create(Class<E> clazz);
}
