package com.linepro.modellbahn.controller.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.service.ItemService;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <M> the element type
 */
public abstract class AbstractItemController<M extends ItemModel> extends AbstractController {

    protected final ItemService<M> service;

     /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    protected AbstractItemController(ItemService<M> service) {
        this.service = service;
    }

    protected ResponseEntity<?> add(M model) {
        logPut(model);

        return status(CREATED).body(service.add(model));
    }

    protected ResponseEntity<?> search(M model, Integer pageNumber, Integer pageSize) {
        logGet(model, pageNumber, pageSize);

        Page<M> page = service.search(model, pageNumber, pageSize);

        return page.hasContent() ? ok(page) : notFound().build();
    }
}
