package com.linepro.modellbahn.service.base;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.model.base.ItemModel;
import com.linepro.modellbahn.repository.base.ItemRepository;
import com.linepro.modellbahn.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <E> the element type
 */
@Slf4j
public abstract class AbstractItemService<M extends ItemModel, E extends Item> extends AbstractService {

    /** The persister. */
    private final ItemRepository<E> persister;

    protected ConversionService conversionService;

    protected final Class<M> modelClass;

    protected final Class<E> entityClass;

    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    @SuppressWarnings("unchecked")
    protected AbstractItemService(ItemRepository<E> persister) {
        this.persister = persister;
        this.entityClass = (Class<E>) ReflectionUtils.getParameterizedTypes(persister.getClass())[0];
        this.modelClass = (Class<M>) ReflectionUtils.getParameterizedTypes(this.getClass())[0];
    }

    @Transactional
    public Optional<M> get(M model) {
        logGet(model);

        return Optional.ofNullable(findByModel(model).map(this::getModel).orElse(null));
    }

    protected abstract Optional<E> findByModel(M model);

    @Transactional
    public M add(M model) {
        logPut(model);

        E entity = getEntity(model);

        E added = persister.saveAndFlush(entity);

        return getModel(added);
    }

    @Transactional
    public Optional<M> update(M model) {
        logPut(model);

        return findByModel(model).map(e -> conversionService.convert(persister.saveAndFlush(merge(e, model)), modelClass));
    }

    @Transactional
    public boolean delete(M model) {
        logDelete(model);

        return findByModel(model).map(e -> {
            persister.delete(e);
            return true;
        }).orElse(false);
    }

    @Transactional
    public Page<M> search(M model, PageRequest pageRequest) {
        E entity = getEntity(model);

        return persister.findAll(Example.of(entity), pageRequest).map(this::getModel);
    }

    protected E merge(E entity, M model) {
        try {
            BeanUtils.copyProperties(entity, model);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("{}", e.getMessage());
        }
        return entity;
    }

    protected M getModel(E entity) {
        return getModel(entity, modelClass);
    }

    protected <T, N> T getModel(N entity, Class<T> clazz) {
        return conversionService.convert(entity, clazz);
    }

    protected E getEntity(M model) {
        return conversionService.convert(model, entityClass);
    }

    protected <T, N> T getEntity(N model, Class<T> clazz) {
        return conversionService.convert(model, clazz);
    }
}
