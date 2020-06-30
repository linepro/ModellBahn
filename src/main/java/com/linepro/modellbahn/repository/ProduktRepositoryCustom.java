package com.linepro.modellbahn.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.entity.Produkt;

public interface ProduktRepositoryCustom {

    @EntityGraph(value = "produkt.noChildren", type = EntityGraphType.FETCH)
    Page<Produkt> findAll(Pageable pageable);

    @EntityGraph(value = "produkt.noChildren", type = EntityGraphType.FETCH)
    <S extends Produkt> Page<S> findAll(Example<S> example, Pageable pageable);

}
