package com.linepro.modellbahn.persistence.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

public class Persister<K, E> implements IPersister<K, E> {

    private final EntityManager entityManager;

    private final Logger logger;

    private final Class<E> clazz;

    private final Map<String,Selector> selectors;

    @Inject
    public Persister(final EntityManager entityManager, final ILoggerFactory logManager,
            @Assisted final Class<E> clazz) {
        this.entityManager = entityManager;
        this.logger = logManager.getLogger(clazz.getName());
        this.clazz = clazz;

        selectors = new SelectorsBuilder().build(clazz, Arrays.asList(Column.class, JoinColumn.class));
        
        info(selectors.values().toString());
    }

    @Override
    public E add(E entity) throws Exception {
        E result = entity;

        try {
            begin();

            result = getEntityManager().merge(entity);

            commit();

            info("saved " + result);
        } catch (Exception e) {
            rollback();

            error("error saving " + entity, e);
            
            throw e;
        }

        return result;
    }
    @Override
    public E update(K id, E entity) {
        E result = entity;

        try {
            begin();

            E found = (E) getEntityManager().find(clazz, id);

            if (found != null) {
                for (Selector selector : selectors.values()) {
                    Object value = selector.getGetter().invoke(entity);

                    if (value != null) {
                        selector.getSetter().invoke(found, value);
                    }
                }

                result = found;
            } else {
                result = getEntityManager().find(clazz, id);
            }

            commit();

            info("updated " + result);
        } catch (Throwable e) {
            rollback();

            error("error updating " + entity, e);
        }

        return result;
    }

    @Override
    public void delete(K id) {
        try {
            begin();

            E entity = (E) getEntityManager().find(clazz, id);

            getEntityManager().remove(entity);

            commit();
            
            info("deleted " + entity);
        } catch (Throwable e) {
            rollback();

            error("error deleting " + id, e);
        }
    }

    @Override
    public void deleteAll() {
        deleteAll(null);
    }
    
    @Override
    public void deleteAll(E template) {
        try {
            begin();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaDelete<E> query = builder.createCriteriaDelete(clazz);
            Root<E> root = query.from(clazz);

            List<Predicate> predicates = getConditions(builder, query, root, template);

            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[] {}));
            }

            getEntityManager().createQuery(query).executeUpdate();

            commit();

            info("deleted " + template);
        } catch (Throwable e) {
            rollback();

            error("error deleting " + template, e);
        }
    }

    @Override
    public E find(K id) {
        E result = null;

        try {
            begin();

            result = getEntityManager().find(clazz, id);

            commit();

            info("found " + result);
        } catch (Throwable e) {
            rollback();

            error("error finding " + id, e);
        }

        return result;
    }

    @Override
    public List<E> findAll() {
        return findAll(null);
    }
    
    @Override
    public List<E> findAll(E template) {
        List<E> result = new ArrayList<>();

        try {
            begin();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<E> query = builder.createQuery(clazz);
            Root<E> root = query.from(clazz);

            List<Predicate> predicates = getConditions(builder, query, root, template);

            if (!predicates.isEmpty()) {
                query.select(root).where(predicates.toArray(new Predicate[] {}));
            } else {
                query.select(root);
            }

            result.addAll(getEntityManager().createQuery(query).getResultList());

            commit();

            info("searched " + result);
        } catch (Throwable e) {
            rollback();

            error("error searching " + template, e);
        }

        return result;
    }

    protected List<Predicate> getConditions(CriteriaBuilder builder, CommonAbstractCriteria query, Root<E> root, E template) throws Throwable {
        List<Predicate> predicates = new ArrayList<Predicate>();
 
        if (template != null) {
            for (Selector selector : selectors.values()) {
                Object value = selector.getGetter().invoke(template);
    
                if (value != null) {
                    predicates.add(builder.equal(root.get(selector.getName()), value));
                }
            }
        }
        
        return predicates;
    }
    
    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void begin() {
        getEntityManager().getTransaction().begin();
    }

    @Override
    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    protected Logger getLogger() {
        return logger;
    }

    protected void info(String message) {
        getLogger().info(message);
    }

    protected void error(String message, Throwable e) {
        getLogger().error(message, e);
    }
}