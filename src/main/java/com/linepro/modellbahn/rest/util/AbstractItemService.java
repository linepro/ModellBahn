package com.linepro.modellbahn.rest.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.util.AbstractItem;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.util.ReflectionUtils;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <E> the element type
 */
public abstract class AbstractItemService<E extends IItem, T extends AbstractItem<T>> extends AbstractService {

    protected static final List<String> PAGE_FIELDS = Arrays.asList(ApiNames.PAGE_NUMBER, ApiNames.PAGE_SIZE);

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

    /** The persister. */
    private final IItemRepository<T> persister;

    private final ObjectMapper objectMapper;

    private final Logger logger;

    private Class<T> persistentClass;

    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    @SuppressWarnings("unchecked")
    protected AbstractItemService(IItemRepository<T> persister) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.objectMapper = new ObjectMapper();
        this.persister = persister;

        persistentClass = (Class<T>) ReflectionUtils.getParameterizedTypes(this.getClass())[1];
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @ApiResponses({@ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> get(Long id) {
        return get(persister.getOne(id));
    }

    protected ResponseEntity<?> get(T entity) {
        logPost("Get: " + entity);

        try {
            return ok(entity);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    /**
     * Adds a new entity.
     * @param entity the entity
     * @return the ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiResponses({@ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> add(E entity) {
        logPost("Add: " + entity);

        try {
            return created(persister.saveAndFlush((T) entity));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @ApiResponses({@ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> update(Long id, E entity) {
        return update(persister.getOne(id), entity);
    }

    @SuppressWarnings("unchecked")
    protected ResponseEntity<?> update(T oldEntity, E newEntity) {
        logPost("Update: " + newEntity);

        try {
            return created(persister.saveAndFlush((T) newEntity));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @ApiResponses({@ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> delete(Long id) {
        return delete(persister.getOne(id));
    }

    protected ResponseEntity<?> delete(T entity) {
        logPost("Delete: " + entity);

        try {
            persister.delete(entity);

            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    /**
     * Searches for entities (by example using Uri query parameters).
     * @param info the info
     * @return the ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiResponses({@ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> search(Map<String, String> queryParameters) {
        logGet("Search: " + queryParameters);

        try {
            Example<T> example = getExample(queryParameters, getPersistentClass());
            PageRequest pageRequest = getPageRequest(queryParameters);

            return subSearch(persister, example, pageRequest);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    protected PageRequest getPageRequest(Map<String, String> queryParameters) {
        String pageNumberStr = queryParameters.get(ApiNames.PAGE_NUMBER);
        String pageSizeStr = queryParameters.get(ApiNames.PAGE_SIZE);

        if (pageNumberStr != null || pageSizeStr != null) {
            return PageRequest.of(NumberUtils.toInt(pageNumberStr, FIRST_PAGE),
                    NumberUtils.toInt(pageSizeStr, DEFAULT_PAGE_SIZE), Sort.unsorted());
        }

        return null;
    }

    protected <N extends AbstractItem<N>> ResponseEntity<?> subSearch(IItemRepository<N> lookup, Example<N> example,
            PageRequest pageRequest) {
        try {
            List<N> entities;

            Page<N> page = null;

            if (pageRequest != null) {
                page = (Page<N>) lookup.findAll(example, pageRequest);

                entities = page.toList();
            } else {
                entities = (List<N>) lookup.findAll(example);
            }

            if (entities.isEmpty()) {
                return noContent();
            }

            return ok(entities);
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    protected List<Link> getNavLinks(PageRequest pageRequest, Page<?> page) {
        List<Link> navigation = new ArrayList<>();
        ServletUriComponentsBuilder.fromCurrentRequestUri();
        if (pageRequest != null) {
            if (page.hasNext()) {
                linkTo(getClass(), ApiPaths.SEARCH).withRel(ApiNames.NEXT);
            }

            if (page.hasPrevious()) {
                linkTo(getClass(), ApiPaths.SEARCH).withRel(ApiNames.PREVIOUS);
            }
        }

        return navigation;
    }

    /**
     * Createa a template for search / delete from query parameters.
     * @param queryParameters the query parameters
     * @return the template
     * @throws Exception if reflection fails
     */
    protected <E> Example<E> getExample(Map<String, String> queryParameters, Class<E> clazz) throws Exception {
        return Example.of(objectMapper.convertValue(queryParameters, clazz));
    }

    protected ResponseEntity<?> getResponse(BodyBuilder builder, IItem entity, boolean modification) {
        return builder.body(entity);
    }

    protected ResponseEntity<?> getResponse(BodyBuilder builder, List<IItem> entities, boolean modification, List<Link> navigation) {
        for (IItem entity : entities) {
            // entity.add(linkTogetServiceURI(), modification);
        }

        return builder.body(entities);
    }

    protected ResponseEntity<?> getResponse(Exception e) {
        if (e instanceof PersistenceException && e.getCause() instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e.getCause()).getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            return badRequest(message);
        } else if (e instanceof EntityExistsException) {
            return badRequest(e.getMessage());
        } else if (e instanceof EntityNotFoundException) {
            return badRequest(e.getMessage());
        } else if (e instanceof NonUniqueResultException) {
            return badRequest(e.getMessage());
        } else if (e instanceof NoResultException) {
            return badRequest(e.getMessage());
        }

        return serverError(e);
    }

    protected org.springframework.hateoas.Link getServiceURI() {
        return WebMvcLinkBuilder.linkTo(getClass()).withRel(IanaLinkRelations.ITEM);
    }

    protected Link getApiLink() {
        return WebMvcLinkBuilder.linkTo(getClass()).withRel(ApiNames.API);
    }

    private Link getHomeLink() {
        return WebMvcLinkBuilder.linkTo(getClass()).withRel(ApiNames.HOME);
    }

    private Link getWADLLink() {
        return WebMvcLinkBuilder.linkTo(getClass()).withRel(ApiNames.WADL);
    }

    public String getEntityClassName() {
        return getPersistentClass().getName();
    }

    private Class<T> getPersistentClass() {
        return persistentClass;
    }
}
