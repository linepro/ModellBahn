package com.linepro.modellbahn.persistence;

import java.util.List;

import javax.persistence.EntityManager;

import com.linepro.modellbahn.model.IItem;

public interface IItemPersister<T extends IItem> {

    T findById(Long id);

    T save(T entity);

    T update(T entity);

    void delete(Long id);

    List<T> search(T template);

    void delete(T template);

    EntityManager getEntityManager();

    void begin();

    void commit();

    void rollback();
}
