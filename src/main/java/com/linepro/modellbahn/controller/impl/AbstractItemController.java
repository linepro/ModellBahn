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
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.request.ItemRequest;
import com.linepro.modellbahn.service.ItemService;
import com.linepro.modellbahn.service.criterion.Criterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;

import lombok.RequiredArgsConstructor;

/**
 * AbstractService.
 * Basic CRUD rest service
 * @author $Author$
 * @version $Id$
 * @param <M> the element type
 */
@RequiredArgsConstructor
public abstract class AbstractItemController<M extends ItemModel, R extends ItemRequest> {

    protected final ItemService<M,R> service;

    protected ResponseEntity<?> add(R request) {
        return added(service.add(request));
    }

    protected ResponseEntity<?> search(Criterion criterion, PageCriteria page) {
        return found(service.search(criterion, page));
    }

    public <I extends ItemModel> ResponseEntity<?> found(Optional<I> body) {
        return body.map(b -> ok().header(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE).body(b))
                   .orElse(notFound().build());
    }

    public <I extends ItemModel> ResponseEntity<?> added(I body) {
        return status(CREATED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE).body(body);
    }

    public <I extends ItemModel> ResponseEntity<?> added(Optional<I> body) {
        return body.map(this::added).orElse(notFound().build());
    }

    public <I extends ItemModel> ResponseEntity<?> updated(I body) {
        return status(ACCEPTED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE).body(body);
    }

    public <I extends ItemModel> ResponseEntity<?> updated(Optional<I> body) {
        return body.map(this::updated).orElse(notFound().build());
    }

    public ResponseEntity<?> deleted(boolean found) {
        return (found ? noContent() : notFound()).build();
    }

    public <I extends ItemModel> ResponseEntity<?> found(Page<I> page) {
//        if (page.hasContent()) {
            PagedResourcesAssembler<I> assembler = new PagedResourcesAssembler<I>(null, null);

            return ok().header(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE)
                       .body(
                assembler.toModel(
                    page.getPageable().isUnpaged() && page.hasContent() ?
                        new PageImpl<>(page.getContent(), page.getPageable(), page.getContent().size()) :
                        page,
                    it -> (RepresentationModel<?>) it
                )
            );
//        }
//
//        return notFound().build();
    }
}
