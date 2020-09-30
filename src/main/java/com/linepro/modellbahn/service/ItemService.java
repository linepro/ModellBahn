package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

public interface ItemService<M> {
    M add(M model);

    Page<M> search(Optional<M> model, Optional<Integer> pageNumber, Optional<Integer> pageSize);
}
