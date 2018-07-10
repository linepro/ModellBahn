package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

public interface IPersister<T extends IItem> {

    T save(T entity);

}
