package com.linepro.modellbahn.controller.base;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.base.ItemModel;

public interface ItemController<M extends ItemModel> {

    ResponseEntity<?> get(M model);

    ResponseEntity<?> add(M model);

    ResponseEntity<?> update(M model);

    ResponseEntity<?> delete(M model);

    ResponseEntity<?> search(Map<String, String> queryParameters);

}
