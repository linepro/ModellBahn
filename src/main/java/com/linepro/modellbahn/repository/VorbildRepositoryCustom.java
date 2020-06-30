package com.linepro.modellbahn.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.entity.Vorbild;

public interface VorbildRepositoryCustom {

    @EntityGraph(value = "vorbild.summary", type = EntityGraphType.FETCH)
    <S extends Vorbild> Page<S> findAll(Example<S> example, Pageable pageable);
}
