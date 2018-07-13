package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

public interface IItemPersister<E extends IItem> extends IPersister<Long, E> {

    E update(E entity) throws Exception;
}