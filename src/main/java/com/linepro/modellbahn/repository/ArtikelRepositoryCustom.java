package com.linepro.modellbahn.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.entity.Artikel;

public interface ArtikelRepositoryCustom {

    @EntityGraph(value = "artikel.noChildren", type = EntityGraphType.FETCH)
    Page<Artikel> findAll(Pageable pageable);

    @EntityGraph(value = "artikel.noChildren", type = EntityGraphType.FETCH)
    <S extends Artikel> Page<S> findAll(Example<S> example, Pageable pageable);
}
