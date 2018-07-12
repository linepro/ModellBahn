package com.linepro.modellbahn.persistence;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import com.google.inject.assistedinject.Assisted;

public class AbstractPersister<E, K> {

    class Selector {
        final String name;

        final Method getter;

        final Method setter;

        public Selector(String name, Method getter, Method setter) {
            this.name = name;
            this.getter = getter;
            this.setter = setter;
        }

        public String getName() {
            return name;
        }

        public Method getGetter() {
            return getter;
        }

        public Method getSetter() {
            return setter;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("name", getName())
                    .append("getter", getGetter())
                    .append("setter", getSetter())
                    .toString();
        }
    }

    private final EntityManager entityManager;

    private final Logger logger;

    private final Class<E> clazz;

    private Set<Selector> selectors = new HashSet<>();

    @Inject
    public AbstractPersister(final EntityManager entityManager, final ILoggerFactory logManager,
            @Assisted final Class<E> clazz) {
        this.entityManager = entityManager;
        this.logger = logManager.getLogger(clazz.getName());
        this.clazz = clazz;

        for (Method getter : clazz.getMethods()) {
            if (getter.isAnnotationPresent(Column.class) || getter.isAnnotationPresent(JoinColumn.class)) {
                try {
                    StringBuffer sb = new StringBuffer(getter.getName());

                    sb.replace(0,1,"s");
                            
                    Method setter = clazz.getMethod(sb.toString(), getter.getReturnType());

                    sb.delete(0,3);
                    sb.replace(0,1,StringUtils.lowerCase(sb.substring(0,1)));

                    selectors.add(new Selector(sb.toString(), getter, setter));
                } catch (NoSuchMethodException | SecurityException e) {
                    error("Error parsing " + clazz.getName(), e);
                }
            }
        }
        
        info(selectors.toString());
    }

    public E save(E entity) {
        E result = entity;

        try {
            begin();

            result = getEntityManager().merge(entity);

            commit();

            info("saved " + result);
        } catch (Throwable e) {
            rollback();

            error("error saving " + entity, e);
        }

        return result;
    }

    public E findById(K id) {
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

    protected List<Predicate> getConditions(CriteriaBuilder builder, CommonAbstractCriteria query, Root<E> root, E template) throws Throwable {
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        for (Selector selector : selectors) {
            Object value = selector.getGetter().invoke(template);

            if (value != null) {
                predicates.add(builder.equal(root.get(selector.getName()), value));
            }
        }
        
        return predicates;
    }

    public List<E> search(E template) {
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

    public E update(E entity, K id) {
        E result = entity;

        try {
            begin();

            E found = (E) getEntityManager().find(clazz, id);

            if (found != null) {
                for (Selector selector : selectors) {
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

    public void deleteById(K id) {
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
    
    public void delete(E template) {
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
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void begin() {
        getEntityManager().getTransaction().begin();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

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