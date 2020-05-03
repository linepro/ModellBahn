package com.linepro.modellbahn.controller.base;

import static org.springframework.http.ResponseEntity.of;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linepro.modellbahn.entity.base.Item;
import com.linepro.modellbahn.model.base.ItemModel;
import com.linepro.modellbahn.service.base.AbstractItemService;
import com.linepro.modellbahn.util.ReflectionUtils;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <M> the element type
 */
public abstract class AbstractItemController<M extends ItemModel, E extends Item> extends AbstractController implements ItemController<M> {

    protected static final List<String> PAGE_FIELDS = Arrays.asList(ApiNames.PAGE_NUMBER, ApiNames.PAGE_SIZE);

    protected static final Integer FIRST_PAGE = 0;

    protected static final Integer DEFAULT_PAGE_SIZE = 30;

    protected final AbstractItemService<M,E> service;

    private final Class<M> modelClass;

    /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    @SuppressWarnings("unchecked")
    protected AbstractItemController(AbstractItemService<M,E> service) {
        this.service = service;
        
        this.modelClass = (Class<M>) ReflectionUtils.getParameterizedTypes(this.getClass())[0];
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> get(M model) {
        logGet(model);

        return of(service.get(model));
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> add(M model) {
        logPut(model);

        return created(service.add(model));
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> update(M model) {
        logPost(model);

        return of(service.update(model));
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> delete(M model) {
        logDelete(model);

        return service.delete(model) ? noContent() : notFound();
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> search(Map<String, String> queryParameters) {
        logGet(queryParameters);

        Page<M> page = service.search(getModel(queryParameters), getPageRequest(queryParameters));

        return page.hasContent() ? ok(page) : noContent();
    }

    protected M getModel(Map<String, String> values) {
        return getModel(values, modelClass);
    }
    
    protected <T> T getModel(Map<String, String> values, Class<T> clazz) {
        return new ObjectMapper().convertValue(values, clazz);
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
}
