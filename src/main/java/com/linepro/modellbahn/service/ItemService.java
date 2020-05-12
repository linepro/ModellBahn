package com.linepro.modellbahn.service;

import org.springframework.data.domain.Page;

public interface ItemService<M> {
    M add(M model);

    Page<M> search(M model, Integer pageNumber, Integer pageSize);
}
