package com.linepro.modellbahn.controller.base;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.base.NamedItemModel;

public interface NamedItemController<M extends NamedItemModel> extends ItemController<M> {

    ResponseEntity<?> get(String name);

    ResponseEntity<?> update(String name, M model);

    ResponseEntity<?> delete(String name);

}
