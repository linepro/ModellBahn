package com.linepro.modellbahn.repository.base;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.entity.NamedItem;

@NoRepositoryBean
@Transactional
public class ItemRepositoryImpl<E extends NamedItem> extends SearchableRepositoryImpl<E> implements ItemRepository<E> {

    public ItemRepositoryImpl(Class<E> persistentClass, EntityManager entityManager) {
        super(persistentClass, entityManager);
    }
}