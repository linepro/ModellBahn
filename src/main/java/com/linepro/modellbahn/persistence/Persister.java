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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;

import com.google.inject.assistedinject.Assisted;
import com.linepro.modellbahn.model.IItem;

public class Persister<T extends IItem> implements IPersister<T> {

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
    }

    private final EntityManager entityManager;
    
    private final Logger logger;

    private final Class<T> clazz;

    private Set<Selector> selectors = new HashSet<>();
   
    @Inject
    public Persister(
            final EntityManager entityManager, final Logger logger, @Assisted final Class<T> clazz) {
        this.entityManager = entityManager;
        this.logger = logger;
        this.clazz = clazz;
        
        for (Method getter : clazz.getMethods()) {
            if (getter.isAnnotationPresent(Column.class) || getter.isAnnotationPresent(JoinColumn.class)) {
                try {
                    Method setter = clazz.getMethod(getter.getName().replace("get", "set"));

                    String name = null;
                    
                    if (getter.isAnnotationPresent(Column.class)) {
                        ((Column) getter.getAnnotation(Column.class)).name();
                    } else {
                        ((JoinColumn) getter.getAnnotation(JoinColumn.class)).name();
                    }

                    selectors.add(new Selector(name,getter,setter));
                } catch (NoSuchMethodException | SecurityException e) {
                    this.logger.error("", e);
                }
            }
        }
    }

    @Override
    public T save(T entity) {
        T result = entity;

        try {
            entityManager.getTransaction().begin();
            result = entityManager.merge(entity);

            entityManager.getTransaction().commit();

            this.logger.info("saved " + result);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            this.logger.error("error saving " + entity, e);
        }

        return result;
    }

    public T findById(Long id) {
        T result = null;

        try {
            entityManager.getTransaction().begin();

            result = entityManager.find(clazz, id);

            entityManager.getTransaction().commit();

            this.logger.info("found " + result);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            this.logger.error("error finding " + id, e);
        }

        return result;
    }

    public List<T> search(T template) {
        List<T> result = new ArrayList<>();
        
        try {
            entityManager.getTransaction().begin();
            
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            
            List<Predicate> predicates = new ArrayList<Predicate>();

            for (Selector selector : selectors) {
                Object value = selector.getGetter().invoke(template);
                
                if (value != null) {
                    predicates.add(builder.equal(root.get(selector.getName()), value));
                }
            }

            if (!predicates.isEmpty()) {
                query.select(root).where(predicates.toArray(new Predicate[]{}));
            } else {
                query.select(root);
            }
            
            result.addAll(entityManager.createQuery(query).getResultList());

            entityManager.getTransaction().commit();

            this.logger.info("searched " + template);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            this.logger.error("error searching " + template, e);
        }
        
        return result;
    }

    public T update(T entity) {
        T result = entity;

        try {
            entityManager.getTransaction().begin();

            T found = (T) entityManager.find(clazz, entity.getId());

            if (found != null) {
                for (Selector selector : selectors) {
                    Object value = selector.getGetter().invoke(entity);
                    
                    if (value != null) {
                        selector.getSetter().invoke(found, value);
                    }
                }
                
                result = found;
            } else {
                result = entityManager.find(clazz, entity.getId());
            }

            entityManager.getTransaction().commit();

            this.logger.info("updated " + result);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            this.logger.error("error updating " + entity, e);
        }
        
        return result;
    }

    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();

            T entity = (T) entityManager.find(clazz, id);

            entityManager.remove(entity);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            this.logger.error("error deleting " + id, e);
        }
    }
}