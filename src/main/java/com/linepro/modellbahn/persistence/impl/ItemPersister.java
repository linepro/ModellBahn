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
import com.linepro.modellbahn.model.keys.IdKey;
import com.linepro.modellbahn.model.keys.ItemKey;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IKey;
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
public class ItemPersister<E extends IItem<?>> implements IPersister<E> {

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

        debug(businessKeyQuery);
    }

    @Override
    public E add(E entity) throws Exception {
        try {
            begin();

            entity.setDeleted(false);

            getEntityManager().persist(entity);

            commit();

            debug("added: " + entity);

            return entity;
        } catch (Exception e) {
            rollback();

            error("add error: " + entity, e);
            
            throw e;
        }
    }

    @Override
    public E update(Long id, E entity) throws Exception {
        return internalUpdate(new IdKey(id), entity, false);
    }

    @Override
    public E update(E entity) throws Exception {
        return internalUpdate(getItemKey(entity), entity, false);
    }

    @Override
    public E update(IKey key, E entity) throws Exception {
        return internalUpdate(key, entity, false);
    }

    @Override
    public E save(E entity) throws Exception {
        return internalUpdate(getItemKey(entity), entity, true);
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
    protected E internalUpdate(IKey key, E entity, boolean addOrUpdate) throws Exception {
        try {
            begin();

            E found = findByKey(entity, false);
            
            if (found == null && !addOrUpdate) {
                throw new EntityNotFoundException();
            }

            E result;
            
            if (found == null) {
                // Save the new entity
                result = entity;
            } else {
                result = found;

                // Copy updated values into existing entity
                for (Selector selector : selectors.values()) {
                    Object value = selector.getGetter().invoke(entity);

                    if (value instanceof Collection) {
                        ((Collection) selector.getSetter().invoke(found)).addAll((Collection) value);
                    } else if (value != null) {
                        selector.getSetter().invoke(result, value);
                    }
                }
            }

            result = inflate(getEntityManager().merge(result), true);

            commit();

            debug((found == null ? "added" : "updated") + ": " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("Update error: " + entity, e);

            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            begin();

            E found = findById(id, false);

            getEntityManager().remove(found);

            commit();

            debug("deleted: " + id);
        } catch (Exception e) {
            rollback();

            error("delete error : " + id, e);
            
            throw e;
        }
    }

    public void delete(E entity) throws Exception {
        delete(getItemKey(entity));
    }

    protected ItemKey getItemKey(E entity) {
        return new ItemKey(entity, businessKeys.values());
    }

    @Override
    public void delete(IKey key) throws Exception {
        try {
            begin();

            E found = findByKey(key, false);

            getEntityManager().remove(found);

            commit();
            
            debug("deleted: " + key);
        } catch (Exception e) {
            rollback();

            error("delete error: " + key, e);
            
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

            debug("deleteAll: " + template);
        } catch (Exception e) {
            rollback();

            error("deleteAll error: " + template, e);
            
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
    
                debug("findById found: " + result);
    
                return result;
            }
            
            return null;
        } catch (Exception e) {
            rollback();

            error("findById error: " + id, e);
            
            throw e;
       }
    }

    @Override
    public E findByKey(Long id, boolean eager) throws Exception {
        return findById(id, eager);
    }

    @Override
    public E findByKey(String name, boolean eager) throws Exception {
        return findByKey(new NameKey(name), eager);
    }

    @Override
    public E findByKey(E entity, boolean eager) throws Exception {
        return findByKey(getItemKey(entity), eager);
    }

    @Override
    public E findByKey(IKey key, boolean eager) throws Exception {
        try {
            begin();

            Query query = getEntityManager().createQuery(businessKeyQuery);

            key.addCriteria(query);
            
            @SuppressWarnings("unchecked")
            List<E> results = (List<E>) query.getResultList();
            
            if (results.size() > 1) {
                throw new NonUniqueResultException();
            }
            
            E result = null;
            
            if (results.size() == 1) {
                result = inflate(results.get(0), eager);
            }
            
            debug("findByKey found: " + result);

            commit();

            return result;
        } catch (Exception e) {
            rollback();

            error("findByKey error: " + key, e);
            
            throw e;
        }
    }

    @Override
    public List<E> findAll() throws Exception {
        return findAll(null);
    }

    @Override
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

            debug("findAll found: " + result);

            return result;
        } catch (Exception e) {
            rollback();

            error("findAll error: " + template, e);
            
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
                    value = value instanceof IItem ? ((IItem<?>) value).getId() : value;
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
    protected void debug(String message) {
        getLogger().debug(message);
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

    /**
     * Info.
     *
     * @param message the message
     */
    protected void info(String message) {
        getLogger().info(message);
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
     * @throws Exception if we are naughty
     */
    protected E create() throws Exception {
        E template = (E) getEntityClass().newInstance();
        return template;
    }

    /**
     * You can't easily override FetchType.LAZY so touch each collection to fill it in as required
     * only goes one level deep.
     * @param entity the entity to inflate
     * @param inflate true if inflation is required
     * @return the same entity you passed in with it's lazy collections populated
     * @throws Exception if there is a DB error
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