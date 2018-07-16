package com.linepro.modellbahn.persistence;

import java.util.List;

import com.linepro.modellbahn.model.IItem;

public interface IPersister<E extends IItem> extends ISessionManager {
    E add(E entity) throws Exception;

    E find(E entity) throws Exception;

    List<E> findAll() throws Exception;

    List<E> findAll(E entity) throws Exception;

    E update(E entity) throws Exception;

    void delete(E entity) throws Exception;

    void deleteAll() throws Exception;

    void deleteAll(E template) throws Exception;

    Class<E> getEntityClass();
}