package com.linepro.modellbahn.persistence.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.collection.spi.PersistentCollection;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.guice.ISessionManagerFactory;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.ItemKey;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.IIdGenerator;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.ISessionManager;
import com.linepro.modellbahn.persistence.util.BusinessKey;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.SelectorsBuilder;

/**
 * ItemPersister.
 * Basic CRUD DAO for items.
 * @author   $Author$
 * @version  $Id$
 *
 * @param <I> the element type
 */
public class ItemPersister<I extends IItem<?>> implements IPersister<I> {

    /** The entity manager. */
    private final ISessionManagerFactory sessionManagerFactory;

    /** The logger. */
    private final Logger logger;

    /** The entity class. */
    private final Class<?> entityClass;

    /** The selectors. */
    private final Map<String, Selector> selectors;

    /** The business keys. */
    private final Map<String, Selector> businessKeys;

    /** Collections on the object */
    private final Map<String, Selector> collections;

    private final String entityName;

    private final IIdGenerator idGenerator;

    private final String businessKeyQuery;

    private String idKey;
    
    /**
     * Instantiates a new item persister.
     *
     * @param sessionManagerFactory the entity manager
     * @param logManager the log manager
     * @param mappedClass the entity class
     */
    @Inject
    public ItemPersister(final ISessionManagerFactory sessionManagerFactory, final ILoggerFactory logManager,
            @Assisted final Class<?> mappedClass) {
        this.sessionManagerFactory = sessionManagerFactory;
        this.logger = logManager.getLogger(getClass().getName().replace("Item", mappedClass.getSimpleName()));
        this.entityClass = mappedClass;
        this.idGenerator = new IdGenerator(sessionManagerFactory);

        businessKeys = new SelectorsBuilder().build(mappedClass, BusinessKey.class);
        collections = new SelectorsBuilder().build(mappedClass, OneToMany.class);
        selectors = new SelectorsBuilder().build(mappedClass, Column.class, JoinColumn.class, OneToMany.class);

        Entity entityAnnotation = mappedClass.getAnnotation(Entity.class);
        entityName = entityAnnotation != null ? entityAnnotation.name() : mappedClass.getSimpleName();

        StringBuilder queryString = new StringBuilder("SELECT e FROM ")
                .append(entityName)
                .append(" e WHERE ");

        int i = 0;

        for (Selector businessKey : businessKeys.values()) {
            if (i == 0) {
                idKey = businessKey.getName();
            } else {
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
    public I add(I entity) throws Exception {
        ISessionManager session = getSession();

        try {
            entity.setDeleted(false);

            session.getEntityManager().persist(entity);

            debug("added: " + entity);

            session.commit();

            return entity;
        } catch (Exception e) {
            error("add error: " + entity, e);
            
            session.rollback();

            throw e;
        }
    }

    @Override
    public I merge(Long id, I entity) throws Exception {
        ISessionManager session = getSession();

        try {
            @SuppressWarnings("unchecked")
            I found =  inflate((I) session.getEntityManager().find(getEntityClass(), id), false);

            return merge(session, found, entity, false);
        } catch (Exception e) {
            error("Merge error: " + entity, e);

            session.rollback();

            throw e;
        }
    }

    @Override
    public I merge(IKey key, I entity) throws Exception {
        ISessionManager session = getSession();

        try {
            I found = internalFindByKey(session, key, false);

            return merge(session, found, entity, false);
        } catch (Exception e) {
            error("Merge error: " + entity, e);

            session.rollback();

            throw e;
        }
    }

    @Override
    public I update(I entity) throws Exception {
        ISessionManager session = getSession();

        try {
            I result = inflate(session.getEntityManager().merge(entity), true);

            debug("updated: " + result);

            session.commit();

            return result;
        } catch (Exception e) {
            error("Update error: " + entity, e);

            session.rollback();

            throw e;
        }
    }

    @Override
    public I save(I entity) throws Exception {
        ISessionManager session = getSession();

        try {
            I found = internalFindByKey(session, getItemKey(entity), false);

            return merge(session, found, entity, true);
        } catch (Exception e) {
            error("Merge error: " + entity, e);

            session.rollback();

            throw e;
        }
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
    private I merge(ISessionManager session, I found, I entity, boolean addOrUpdate) throws Exception {
        if (found == null && !addOrUpdate) {
            return null;
        }

        I result;
        
        if (found == null) {
            // Save the new entity
            result = entity;
        } else {
            result = found;

            // Copy updated values into existing entity
            for (Selector selector : selectors.values()) {
                Object value = selector.getGetter().invoke(entity);

                if (value instanceof Collection) {
                    ((Collection) selector.getGetter().invoke(found)).addAll((Collection) value);
                } else if (value != null || selector.isNullable()) {
                    selector.getSetter().invoke(result, value);
                }
            }
        }

        result = inflate(session.getEntityManager().merge(result), true);

        debug((found == null ? "added" : "merged") + ": " + result);

        session.commit();

        return result;
    }

    @Override
    public void delete(Long id) throws Exception {
        ISessionManager session = getSession();
            try {

            I found = findById(id, false);

            session.getEntityManager().remove(found);

            debug("deleted: " + id);

            session.commit();
        } catch (Exception e) {
            error("delete error : " + id, e);
            
            session.rollback();

            throw e;
        }
    }

    public void delete(I entity) throws Exception {
        delete(getItemKey(entity));
    }

    private ItemKey getItemKey(I entity) {
        return new ItemKey(entity, businessKeys.values());
    }

    @Override
    public void delete(IKey key) throws Exception {
        ISessionManager session = getSession();

        try {
            I found = internalFindByKey(session, key, false);

            session.getEntityManager().remove(found);
            
            debug("deleted: " + key);

            session.commit();
        } catch (Exception e) {
            error("delete error: " + key, e);
            
            session.rollback();

            throw e;
        }
    }

    @Override
    public void deleteAll() throws Exception {
        deleteAll(null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void deleteAll(I template) throws Exception {
        ISessionManager session = getSession();

        try {
            CriteriaBuilder builder = session.getEntityManager().getCriteriaBuilder();
            CriteriaDelete query = builder.createCriteriaDelete(getEntityClass());
            Root root = query.from(getEntityClass());

            List<Predicate> predicates = getConditions(builder, root, template, selectors, Collections.emptyMap(), null);

            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[] {}));
            }

            session.getEntityManager().createQuery(query).executeUpdate();

            debug("deleteAll: " + template);

            session.commit();
        } catch (Exception e) {
            error("deleteAll error: " + template, e);

            session.rollback();
            
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public I findById(Long id, boolean eager) throws Exception {
        ISessionManager session = getSession();

        try {
            I result = null;
            
            if (id != null) {
                result = inflate((I) session.getEntityManager().find(getEntityClass(), id), eager);
            }

            debug("findById found: " + result);
    
            session.commit();

            return result;
        } catch (Exception e) {
            error("findById error: " + id, e);

            session.rollback();
            
            throw e;
       }
    }

    @Override
    public I findByKey(Long id, boolean eager) throws Exception {
        return findById(id, eager);
    }

    @Override
    public I findByKey(String name, boolean eager) throws Exception {
        return findByKey(new NameKey(name), eager);
    }

    @Override
    public I findByKey(I entity, boolean eager) throws Exception {
        return findByKey(getItemKey(entity), eager);
    }

    @Override
    public I findByKey(IKey key, boolean eager) throws Exception {
        ISessionManager session = getSession();

        try {
            I result = internalFindByKey(session, key, eager);
            
            debug("findByKey found: " + result);

            session.commit();

            return result;
        } catch (Exception e) {
            error("findByKey error: " + key, e);

            session.rollback();
           
            throw e;
        }
    }

    private I internalFindByKey(ISessionManager session, IKey key, boolean eager) throws Exception {
        Query query = session.getEntityManager().createQuery(businessKeyQuery);

        key.addCriteria(query);
        
        @SuppressWarnings("unchecked")
        List<I> results = (List<I>) query.getResultList();
        
        if (results.size() > 1) {
            throw new NonUniqueResultException();
        }
        
        I result = null;
        
        if (results.size() == 1) {
            result = inflate(results.get(0), eager);
        }

        return result;
    }

    @Override
    public Long countAll() throws Exception {
        return countAll(null, Collections.emptyMap());
    }

    @Override
    public Long countAll(I template) throws Exception {
        return countAll(null, Collections.emptyMap());
    }

    @Override
    public Long countAll(I template, Map<String,List<String>> references) throws Exception {
        ISessionManager session = getSession();

        try {
            CriteriaBuilder builder = session.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<?> root = countQuery.from(getEntityClass());
            countQuery.select(builder.count(root));

            List<Predicate> predicates = getConditions(builder, root, template, selectors, references, countQuery);

            if (!predicates.isEmpty()) {
                countQuery.where(predicates.toArray(new Predicate[] {}));
            }

            Long result = session.getEntityManager().createQuery(countQuery).getSingleResult();

            debug("countAll found: " + result);

            session.commit();

            return result;
        } catch (Exception e) {
            error("countAll error: " + template, e);

            session.rollback();
            
            throw e;
        }
    }

    @Override
    public List<I> findAll() throws Exception {
        return findAll(null);
    }

    @Override
    public List<I> findAll(I template) throws Exception {
        return findAll(template, null, null);
    }

    @Override
    public List<I> findAll(I template, Integer startPosition, Integer maxSize) throws Exception {
        return findAll(template, Collections.emptyMap(), startPosition, maxSize);
    }

    @Override
    public List<I> findAll(I template, Map<String,List<String>> references, Integer startPosition, Integer maxSize) throws Exception {
        return findAll(template, references, selectors, startPosition, maxSize);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private List<I> findAll(I template, Map<String,List<String>> references, Map<String, Selector> selectors, Integer startPosition, Integer maxResult) throws Exception {
        ISessionManager session = getSession();

        try {
            CriteriaBuilder builder = session.getEntityManager().getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(getEntityClass());
            Root root = criteria.from(getEntityClass());

            List<Predicate> predicates = getConditions(builder, root, template, selectors, references, criteria);

            if (!predicates.isEmpty()) {
                criteria.select(root).where(predicates.toArray(new Predicate[] {}));
            } else {
                criteria.select(root);
            }

            TypedQuery<?> query = session.getEntityManager().createQuery(criteria);

            if (startPosition != null) {
                query.setFirstResult(startPosition * maxResult);
            }

            if (maxResult != null) {
                query.setMaxResults(maxResult);
            }

            List<I> result = (List<I>) query.getResultList();

            debug("findAll found: " + result);

            session.commit();

            return result;
        } catch (Exception e) {
            error("findAll error: " + template, e);
            
            session.rollback();

            throw e;
        }
    }

    /**
     * Gets the conditions.
     *
     * @param builder the builder
     * @param root the root
     * @param template the template
     * @return the conditions
     * @throws Exception the exception
     */
    private List<Predicate> getConditions(CriteriaBuilder builder, Root<?> root, I template, Map<String, Selector> selectors,
                                          Map<String,List<String>> references, CriteriaQuery<?> criteria) throws Exception {
        List<Predicate> predicates = new ArrayList<>();

        if (template != null) {
            for (Selector selector : selectors.values()) {
                Object value = selector.getGetter().invoke(template);

                if (value != null && !(value instanceof Collection)) {
                    value = value instanceof IItem ? ((IItem<?>) value).getId() : value;
                    Path<Object> field = root.get(selector.getName());
                    Predicate predicate = builder.equal(field, value);
                    predicates.add(predicate);
                }
            }
        }

        if (!references.isEmpty()) {
            for (String key : references.keySet()) {
                Join<?,?> join = root.join(key);
                Expression<String> exp = join.get(DBNames.NAME);
                Predicate predicate = exp.in(references.get(key));
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    @Override
    public void populateLazyCollection(Collection<?> collection) {
        ISessionManager session = getSession();
        
        collection.size();
        
        session.commit();
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    private Logger getLogger() {
        return logger;
    }


    /**
     * Info.
     *
     * @param message the message
     */
    private void debug(String message) {
        getLogger().debug(message);
    }

    /**
     * Error.
     *
     * @param message the message
     * @param e the e
     * @throws Exception the exception
     */
    private void error(String message, Exception e) throws Exception {
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
    public Class<?> getEntityClass() {
        return entityClass;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }

    @Override
    public Map<String, Selector> getSelectors() {
        return selectors;
    }

    /**
     * Creates the.
     *
     * @return the e
     * @throws Exception if we are naughty
     */
    @SuppressWarnings("unchecked")
    @Override
    public I create() throws Exception {
        return (I) getEntityClass().newInstance();
    }

    /**
     * You can't easily override FetchType.LAZY so touch each collection to fill it in as required
     * only goes one level deep.
     * @param entity the entity to inflate
     * @param eager true if inflation is required
     * @return the same entity you passed in with it's lazy collections populated
     * @throws Exception if there is a DB error
     */
    private I inflate(I entity, boolean eager) throws Exception {
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

    private ISessionManager getSession() {
        SessionManager sessionManager = sessionManagerFactory.create();
        sessionManager.begin();
        return sessionManager;
    }

    @Override
    public String getNextId() {
        return idGenerator.getNextId(getEntityName(), idKey);
    }
}