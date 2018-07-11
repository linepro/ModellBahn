package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

public interface IItemPersisterFactory {
    <T extends IItem> IItemPersister<T> create(Class<T> clazz);
}
