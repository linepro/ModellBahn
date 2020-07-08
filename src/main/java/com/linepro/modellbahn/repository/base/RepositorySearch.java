package com.linepro.modellbahn.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositorySearch<E> {

    <S extends E> Page<S> findAll(Criterion criterion, Pageable pageable);
}
