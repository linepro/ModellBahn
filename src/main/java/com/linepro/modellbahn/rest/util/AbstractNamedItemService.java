package com.linepro.modellbahn.rest.util;

import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public abstract class AbstractNamedItemService<E extends INamedItem, T extends AbstractNamedItem<T>> extends AbstractItemService<E,T> {

    protected final INamedItemRepository<T> persister;
    
    protected AbstractNamedItemService(INamedItemRepository<T> persister) {
        super(persister);
        
        this.persister = persister;
   }

    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> get(String name) {
        logPost("Get: " + name);

        try {
            return ok(persister.findByName(name));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @SuppressWarnings("unchecked")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> update(String name, E entity) {
        logPost("Update: " + entity);

        try {
            return created(persister.saveAndFlush((T) entity));
        } catch (Exception e) {
            return getResponse(e);
        }
    }

    @ApiResponses({
        @ApiResponse(code = 500, message = "Internal Server Error")
        })
    public  ResponseEntity<?> delete(String name) {
        logPost("Delete: " + name);

        try {
            persister.deleteByName(name);
            
            return noContent();
        } catch (Exception e) {
            return getResponse(e);
        }
    }
}
