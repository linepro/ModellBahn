package com.linepro.modellbahn.persistence.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.JoinColumn;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.collection.spi.PersistentCollection;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

/**
 * ItemPersister.
 * Basic CRUD DAO for items.
 * @author   $Author$
 * @version  $Id$
 *
 * @param <E> the element type
 */
public class ItemPersister<E extends IItem> implements IPersister<E> {

    /** The entity manager. */
    private final EntityManager entityManager;

    /** The logger. */
    private final Logger logger;

    /** The entity class. */
    private final Class<E> entityClass;

    /** The selectors. */
    private final Map<String, Selector> selectors;

    /** The business keys. */
    private final Map<String, Selector> businessKeys;

    /** Collections on the object */
    private final Map<String, Selector> collections;

    private final String entityName;

    private String businessKeyQuery;

    /**
     * Instantiates a new item persister.
     *
     * @param entityManager the entity manager
     * @param logManager the log manager
     * @param entityClass the entity class
     */
    @Inject
    public ItemPersister(final EntityManager entityManager, final ILoggerFactory logManager,
            @Assisted final Class<E> entityClass) {
        this.entityManager = entityManager;
        this.logger = logManager.getLogger(entityClass.getName());
        this.entityClass = entityClass;

        businessKeys = new SelectorsBuilder().build(entityClass, BusinessKey.class);
        collections = new SelectorsBuilder().build(entityClass, OneToMany.class);
        selectors = new SelectorsBuilder().build(entityClass, Column.class, JoinColumn.class);

        Entity entityAnnotation = ((Entity) entityClass.getAnnotation(Entity.class));
        entityName = entityAnnotation != null ? entityAnnotation.name() : entityClass.getSimpleName();

        StringBuffer queryString = new StringBuffer("SELECT e FROM ")
                .append(entityName)
                .append(" e WHERE ");

        int i = 0;

        for (Selector businessKey : businessKeys.values()) {
            if (i != 0) {
                queryString.append(" AND ");
            }
            queryString.append("e.");
            queryString.append(businessKey.getName());
            queryString.append(" = :");
            queryString.append(businessKey.getName());
            i++;
        }

        businessKeyQuery = queryString.toString();

        info(businessKeys.values().toString());
        info(businessKeyQuery);
        info(collections.values().toString());
        info(selectors.values().toString());
    }

    @Override
    public E add(E entity) throws Exception {
        try {
            begin();

            getEntityManager().persist(entity);

            commit();

            info("add " + entity);

            return entity;
        } catch (Exception e) {
            rollback();

            error("error add " + entity, e);
            
            throw e;
        }
    }

    @Override
    public E update(E entity) throws Exception {
        return internalUpdate(entity, false);
    }

    @Override
    public E save(E entity) throws Exception {
        return internalUpdate(entity, true);
    }

    /**
     * Internal update.
     *
     * @param entity the entity
     * @param addOrUpdate the add or update
     * @return the e
     * @throws Exception the exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected E internalUpdate(E entity, boolean addOrUpdate) throws Exception {
        try {
            begin();

            E result = findByKey(entity, false);
            
            if (result == null && !addOrUpdate) {
                throw new EntityNotFoundException();
            }
            
            if (result == null) {
                // Save the new entity
                result = entity;
            } else {
                // Copy updated values into existing entity
                for (Selector selector : selectors.values()) {
                    Object value = selector.getGetter().invoke(entity);

                    if (value instanceof Collection) {
                        ((Collection) selector.getSetter().invoke(result)).addAll((Collection) value);
                    } else if (value != null) {
                        selector.getSetter().invoke(result, value);
                    }
                }
            }

            result = inflate(getEntityManager().merge(result), true);

            commit();

            info("internalUpdate " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("error internalUpdate " + entity, e);

            throw e;
        }
    }

    @Override
    public void delete(E entity) throws Exception {
        try {
            begin();

            E found = (E) getEntityManager().find(entityClass, entity.getId());

            getEntityManager().remove(found);

            commit();

            info("delete " + entity);
        } catch (Exception e) {
            rollback();

            error("error delete " + entity, e);
            
            throw e;
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

            List<Predicate> predicates = getConditions(builder, query, root, template, selectors);

            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[] {}));
            }

            getEntityManager().createQuery(query).executeUpdate();

            commit();

            info("deleteAll " + template);
        } catch (Exception e) {
            rollback();

            error("error deleteAll " + template, e);
            
            throw e;
        }
    }

    @Override
    public E findById(Long id, boolean eager) throws Exception {
        try {
            if (id != null) {
                begin();
    
                E result = inflate(getEntityManager().find(entityClass, id), eager);
    
                commit();
    
                info("findById " + result);
    
                return result;
            }
            
            return null;
        } catch (Exception e) {
            rollback();

            error("error findById " + id, e);
            
            throw e;
       }
    }

    @Override
    public E findByKey(E entity, boolean eager) throws Exception {
        try {
            begin();

            Query query = getEntityManager().createQuery(businessKeyQuery);

            for (Selector businessKey : businessKeys.values()) {
                Object value = businessKey.getGetter().invoke(entity);
                //value = value instanceof IItem ? ((IItem) value).getId() : value;
                query = query.setParameter(businessKey.getName(), value);
            }
            
            @SuppressWarnings("unchecked")
            List<E> results = (List<E>) query.getResultList();
            
            if (results.size() > 1) {
                throw new NonUniqueResultException();
            }
            
            E result = null;
            
            if (results.size() == 1) {
                result = inflate(results.get(0), eager);
            }
            
            info("findByKey " + result);

            commit();

            return result;
        } catch (Exception e) {
            rollback();

            error("error findByKey " + entity, e);
            
            throw e;
        }
    }

    @Override
    public List<E> findAll() throws Exception {
        return findAll(null);
    }
    public List<E> findAll(E template) throws Exception {
        return findAll(template, selectors);
    }

    protected List<E> findAll(E template, Map<String,Selector> selectors) throws Exception {
        try {
            List<E> result = new ArrayList<>();

            begin();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<E> query = builder.createQuery(entityClass);
            Root<E> root = query.from(entityClass);

            List<Predicate> predicates = getConditions(builder, query, root, template, selectors);

            if (!predicates.isEmpty()) {
                query.select(root).where(predicates.toArray(new Predicate[] {}));
            } else {
                query.select(root);
            }

            result.addAll(getEntityManager().createQuery(query).getResultList());

            commit();

            info("findAll " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("error findAll " + template, e);
            
            throw e;
        }
    }

    /**
     * Gets the conditions.
     *
     * @param builder the builder
     * @param query the query
     * @param root the root
     * @param template the template
     * @return the conditions
     * @throws Exception the exception
     */
    protected List<Predicate> getConditions(CriteriaBuilder builder, CommonAbstractCriteria query, Root<E> root,
            E template, Map<String,Selector> selectors) throws Exception {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (template != null) {
            for (Selector selector : selectors.values()) {
                Object value = selector.getGetter().invoke(template);

                if (value != null) {
                    value = value instanceof IItem ? ((IItem) value).getId() : value;
                    Path<Object> field = root.get(selector.getName());
                    Predicate predicate = builder.equal(field, value);
                    predicates.add(predicate);
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

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Info.
     *
     * @param message the message
     */
    protected void info(String message) {
        getLogger().info(message);
    }

    /**
     * Error.
     *
     * @param message the message
     * @param e the e
     * @throws Exception the exception
     */
    protected void error(String message, Exception e) throws Exception {
        getLogger().error(message, e);

        throw e;
    }

    
    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * Gets the selectors.
     *
     * @return the selectors
     */
    protected Map<String, Selector> getSelectors() {
        return selectors;
    }

    /**
     * Creates the.
     *
     * @return the e
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    protected E create() throws InstantiationException, IllegalAccessException {
        E template = (E) getEntityClass().newInstance();
        return template;
    }

    /**
     * You can't easily override FetchType.LAZY so touch each collection to fill it in as required
     * @param entity
     * @return
     * @throws Exception
     */
    protected E inflate(E entity, boolean eager) throws Exception {
        if (eager && entity != null) {
            for (Selector selector : collections.values()) {
                Collection<?> collection = (Collection<?>) selector.getGetter().invoke(entity);
                
                if (collection instanceof PersistentCollection) {
                    ((PersistentCollection) collection).forceInitialization();
                }
            }
        }
        
        return entity;
    }
}