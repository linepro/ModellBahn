package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

public interface INamedItemPersister<E extends IItem> extends IPersister<E> {
    E findByName(String name) throws Exception;
}