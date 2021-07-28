package com.linepro.modellbahn.service;

import org.springframework.data.domain.Page;

import com.linepro.modellbahn.service.criterion.Criterion;
import com.linepro.modellbahn.service.criterion.PageCriteria;

public interface ItemService<M,R> {
    M add(R model);

    Page<M> search(Criterion criterion, PageCriteria page);
}
