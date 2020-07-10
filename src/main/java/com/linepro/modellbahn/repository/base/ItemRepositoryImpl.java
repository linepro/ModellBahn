package com.linepro.modellbahn.repository.base;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.NamedItem;

@NoRepositoryBean
@Transactional
public class ItemRepositoryImpl<E extends NamedItem> extends SimpleJpaRepository<E, Long> implements ItemRepository<E> {

    private final Class<E> persistentClass;
    
    private final EntityManager entityManager;

    public ItemRepositoryImpl(Class<E> persistentClass, EntityManager entityManager) {
        super(persistentClass, entityManager);

        this.persistentClass = persistentClass;
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends E> Page<S> findAll(Criterion criterion, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(persistentClass);

        Root<E> root = criteriaQuery.from(persistentClass);
        Predicate[] where = criterion.getCriteria(criteriaBuilder, root);
        criteriaQuery = criteriaQuery.where(where);

        TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery = applyPaging(typedQuery, pageable);

        Long totalRows = 0L;

        if (pageable.isPaged()) {
            typedQuery = typedQuery.setMaxResults(pageable.getPageSize())
                                   .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

            totalRows = getTotalRows(criteriaBuilder, criteriaQuery, where);
        }

        typedQuery = applyGraph(typedQuery, criterion);

        return (Page<S>) new PageImpl<E>(typedQuery.getResultList(), pageable, totalRows);
    }

    protected TypedQuery<E> applyGraph(TypedQuery<E> typedQuery, Criterion criterion) {
        if (StringUtils.hasText(criterion.getGraphName())) {
            return typedQuery.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph(criterion.getGraphName()));
        }

        return typedQuery;
    }

    protected TypedQuery<E> applyPaging(TypedQuery<E> typedQuery, Pageable pageable) {
        if (pageable.isPaged()) {
            typedQuery = typedQuery.setMaxResults(pageable.getPageSize())
                                   .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        }

        return typedQuery;
    }
    
    protected Long getTotalRows(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Predicate[] where) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(persistentClass)));
        countQuery.where(where);

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}