package com.linepro.modellbahn.persistence;

import java.util.List;

public interface IPersister<K, E> extends ISessionManager {
    E add(E entity) throws Exception;

    E find(K id) throws Exception;

    List<E> findAll() throws Exception;

    List<E> findAll(E entity) throws Exception;

    E update(K id, E entity) throws Exception;

    void delete(K id) throws Exception;

    void deleteAll() throws Exception;

    void deleteAll(E template) throws Exception;
}