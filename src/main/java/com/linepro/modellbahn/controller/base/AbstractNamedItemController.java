package com.linepro.modellbahn.controller.base;

import java.util.Collections;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.entity.base.NamedItem;
import com.linepro.modellbahn.model.base.NamedItemModel;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public abstract class AbstractNamedItemController<M extends NamedItemModel, E extends NamedItem>
        extends AbstractItemController<M, E> implements NamedItemController<M> {

    protected final AbstractNamedItemService<M, E> service;

    protected AbstractNamedItemController(AbstractNamedItemService<M, E> service) {
        super(service);

        this.service = service;
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
     public ResponseEntity<?> get(String name) {
        return super.get(getModel(name));
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> update(String name, M model) {
        model.setName(name);

        return super.update(model);
    }

    @Override
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public ResponseEntity<?> delete(String name) {
        return super.delete(getModel(name));
    }

    protected M getModel(String name) {
        return getModel(Collections.singletonMap("name", name));
    }
}
