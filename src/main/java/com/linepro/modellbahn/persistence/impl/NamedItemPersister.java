package com.linepro.modellbahn.persistence.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.ILoggerFactory;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.IPersister;

public class NamedItemPersister<E extends INamedItem> extends ItemPersister<E> implements IPersister<E> {

    @Inject
    public NamedItemPersister(final EntityManager entityManager, final ILoggerFactory logManager, @Assisted final Class<E> clazz) {
        super(entityManager, logManager, clazz);
        
        ConvertUtils.register(new NamedItemConverter<E>(this), clazz);
    }
}