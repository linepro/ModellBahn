package com.linepro.modellbahn.controller.impl;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.service.ItemService;

import lombok.RequiredArgsConstructor;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <M> the element type
 */
@RequiredArgsConstructor
public abstract class AbstractItemController<M extends ItemModel> {

    protected final ItemService<M> service;

    protected ResponseEntity<?> add(M model) {
        return added(service.add(model));
    }

    protected ResponseEntity<?> search(Optional<M> model, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        return found(service.search(model, pageNumber, pageSize));
    }

    public <I extends ItemModel> ResponseEntity<?> found(Optional<I> body) {
        return body.map(b -> ok(b)).orElse(notFound().build());
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

    public <I extends ItemModel> ResponseEntity<?> found(Page<I> page) {
        if (page.hasContent()) {
            PagedResourcesAssembler<I> assembler = new PagedResourcesAssembler<I>(null, null);

            return ok(
                assembler.toModel(
                    page.getPageable().isUnpaged() && page.hasContent() ?
                        new PageImpl<>(page.getContent(), page.getPageable(), page.getContent().size()) :
                        page,
                    it -> (RepresentationModel<?>) it
                )
            );
        }

        return notFound().build();
    }
}
