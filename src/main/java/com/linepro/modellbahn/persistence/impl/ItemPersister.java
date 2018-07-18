package com.linepro.modellbahn.persistence.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

public class ItemPersister<E extends IItem> implements IPersister<E> {

    private final EntityManager entityManager;

    private final Logger logger;

    private final Class<E> entityClass;

    private final Map<String, Selector> selectors;

    @Inject
    public ItemPersister(final EntityManager entityManager, final ILoggerFactory logManager,
            @Assisted final Class<E> entityClass) {
        this.entityManager = entityManager;
        this.logger = logManager.getLogger(entityClass.getName());
        this.entityClass = entityClass;

        selectors = new SelectorsBuilder().build(entityClass, Arrays.asList(Column.class, JoinColumn.class));

        info(selectors.values().toString());
    }

    @Override
    public E add(E entity) throws Exception {
        try {
            begin();

            getEntityManager().persist(entity);

            commit();

            info("saved " + entity);

            return entity;
        } catch (Exception e) {
            rollback();

            error("error saving " + entity, e);
        }

        return null;
    }

    @Override
    public E update(E entity) throws Exception {
        try {
            begin();

            E result = find(entity);

            if (result != null) {
                for (Selector selector : selectors.values()) {
                    Object value = selector.getGetter().invoke(entity);

                    if (value != null) {
                        selector.getSetter().invoke(result, value);
                    }
                }

                result = getEntityManager().merge(result);
            }

            commit();

            info("updated " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("error updating " + entity, e);
        }

        return null;
    }

    @Override
    public E save(E entity) throws Exception {
        E result = update(entity);
        
        if (result == null) {
            result = add(entity);
        }

        return result;
    }

    @Override
    public void delete(E entity) throws Exception {
        try {
            begin();

            E found = (E) getEntityManager().find(entityClass, entity.getId());

            getEntityManager().remove(found);

            commit();

            info("deleted " + entity);
        } catch (Exception e) {
            rollback();

            error("error deleting " + entity, e);
        }
    }

    @Override
    public void deleteAll() throws Exception {
        deleteAll(null);
    }

    @Override
    public void deleteAll(E template) throws Exception {
        try {
            begin();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaDelete<E> query = builder.createCriteriaDelete(entityClass);
            Root<E> root = query.from(entityClass);

            List<Predicate> predicates = getConditions(builder, query, root, template);

            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[] {}));
            }

            getEntityManager().createQuery(query).executeUpdate();

            commit();

            info("deleted " + template);
        } catch (Exception e) {
            rollback();

            error("error deleting " + template, e);
        }
    }

    @Override
    public E find(E entity) throws Exception {
        try {
            if (entity.getId() != null) {
                begin();
    
                E result = getEntityManager().find(entityClass, entity.getId());
    
                commit();
    
                info("found " + result);
    
                return result;
            }
        } catch (Exception e) {
            rollback();

            error("error finding " + entity.getId(), e);
        }

        return null;
    }

    @Override
    public List<E> findAll() throws Exception {
        return findAll(null);
    }

    @Override
    public List<E> findAll(E template) throws Exception {
        try {
            List<E> result = new ArrayList<>();

            begin();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<E> query = builder.createQuery(entityClass);
            Root<E> root = query.from(entityClass);

            List<Predicate> predicates = getConditions(builder, query, root, template);

            if (!predicates.isEmpty()) {
                query.select(root).where(predicates.toArray(new Predicate[] {}));
            } else {
                query.select(root);
            }

            result.addAll(getEntityManager().createQuery(query).getResultList());

            commit();

            info("searched " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("error searching " + template, e);
        }

        return null;
    }

    protected List<Predicate> getConditions(CriteriaBuilder builder, CommonAbstractCriteria query, Root<E> root,
            E template) throws Exception {
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
    public void populateLazyCollection(Collection<?> collection) throws Exception {
        begin();
        
        collection.size();
        
        commit();
    }

    @Override
    public void populateLazyCollections() throws Exception {
        
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void begin() {
        if (!getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().begin();
        }
    }

    @Override
    public void commit() {
        if (getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().commit();
        }
    }

    @Override
    public void rollback() {
        if (getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().rollback();
        }
    }

    protected Logger getLogger() {
        return logger;
    }

    protected void info(String message) {
        getLogger().info(message);
    }

    protected void error(String message, Exception e) throws Exception {
        getLogger().error(message, e);

        throw e;
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    protected Map<String, Selector> getSelectors() {
        return selectors;
    }

    protected E create() throws InstantiationException, IllegalAccessException {
        E template = (E) getEntityClass().newInstance();
        return template;
    }
}