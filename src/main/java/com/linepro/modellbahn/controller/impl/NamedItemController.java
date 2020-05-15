package com.linepro.modellbahn.controller.impl;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.NamedItemModel;
import com.linepro.modellbahn.service.NamedItemService;

public class NamedItemController<M extends NamedItemModel> extends AbstractItemController<M> {

    protected final NamedItemService<M> service;

    protected NamedItemController(NamedItemService<M> service) {
        super(service);

        this.service = service;
    }

    protected ResponseEntity<?> get(String name) {
        return added(service.get(name));
    }

    protected ResponseEntity<?> update(String name, M model) {
        return updated(service.update(name, model));
    }

    protected ResponseEntity<?> delete(String name) {
        return deleted(service.delete(name));
    }
}
