package com.linepro.modellbahn.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.entity.Decoder;

public interface DecoderRepositoryCustom {

    @EntityGraph(value = "decoder.noChildren", type = EntityGraphType.FETCH)
    Page<Decoder> findAll(Pageable pageable);

    @EntityGraph(value = "decoder.noChildren", type = EntityGraphType.FETCH)
    <S extends Decoder> Page<S> findAll(Example<S> example, Pageable pageable);
}
