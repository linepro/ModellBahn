package com.linepro.modellbahn.controller.impl;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.Optional;

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
public abstract class AbstractItemController<M extends ItemModel> {

    protected final ItemService<M> service;

     /**
     * Instantiates a new abstract service.
     * @param entityClass the entity class
     */
    protected AbstractItemController(ItemService<M> service) {
        this.service = service;
    }

    protected ResponseEntity<?> add(M model) {
        return added(service.add(model));
    }

    protected ResponseEntity<?> search(M model, Integer pageNumber, Integer pageSize) {
        return found(service.search(model, pageNumber, pageSize));
    }

    public <I extends ItemModel> ResponseEntity<?> added(I body) {
        return status(CREATED).body(body);
    }

    public <I extends ItemModel> ResponseEntity<?> added(Optional<I> body) {
        return body.map(this::added).orElse(notFound().build());
    }

    public <I extends ItemModel> ResponseEntity<?> updated(I body) {
        return status(ACCEPTED).body(body);
    }

    public <I extends ItemModel> ResponseEntity<?> updated(Optional<I> body) {
        return body.map(this::updated).orElse(notFound().build());
    }

    public ResponseEntity<?> deleted(boolean found) {
        return (found ? noContent() : notFound()).build();
    }

    public <I extends ItemModel> ResponseEntity<?> found(Page<M> page) {
        return page.hasContent() ? ok(page) : notFound().build();
    }
}
