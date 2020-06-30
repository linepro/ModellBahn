package com.linepro.modellbahn.repository.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linepro.modellbahn.entity.NamedItem;

public interface NamedItemRepositoryCustom<T extends NamedItem> {
    
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

}
