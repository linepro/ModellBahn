package com.linepro.modellbahn.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.entity.DecoderTyp;

public interface DecoderTypRepositoryCustom {

    @EntityGraph(value = "decoderTyp.noChildren", type = EntityGraphType.FETCH)
    Page<DecoderTyp> findAll(Pageable pageable);

    @EntityGraph(value = "decoderTyp.noChildren", type = EntityGraphType.FETCH)
    <S extends DecoderTyp> Page<S> findAll(Example<S> example, Pageable pageable);

}
