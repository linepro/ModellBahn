package com.linepro.modellbahn.persistence;

import java.util.List;

import javax.persistence.EntityManager;

public interface IPersister<E, K> {

    E save(E entity);

    E findById(K id);

    List<E> search(E template);

    E update(E entity, K id);

    void deleteById(K id);

    void delete(E template);

    EntityManager getEntityManager();

    void begin();

    void commit();

    void rollback();

}