package com.linepro.modellbahn.controller.impl;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.request.NamedItemRequest;
import com.linepro.modellbahn.service.NamedItemService;

public class NamedItemController<M extends NamedItemModel, R extends NamedItemRequest> extends AbstractItemController<M,R> {

    protected final NamedItemService<M,R> service;

    protected NamedItemController(NamedItemService<M,R> service) {
        super(service);

        this.service = service;
    }

    protected ResponseEntity<?> get(String name) {
        return found(service.get(name));
    }

    protected ResponseEntity<?> update(String name, R request) {
        return updated(service.update(name, request));
    }

    protected ResponseEntity<?> delete(String name) {
        return deleted(service.delete(name));
    }
}
