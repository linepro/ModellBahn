package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "VorbildRepository")
public interface VorbildRepository extends NamedItemRepository<Vorbild> {

    @EntityGraph(value = "vorbild.detail", type = EntityGraphType.FETCH)
    Optional<Vorbild> findByName(@Param(ApiNames.NAMEN) String name);

    @EntityGraph(value = "vorbild.summary", type = EntityGraphType.FETCH)
    <S extends Vorbild> Page<S> findAll(Example<S> example, Pageable pageable);

    @EntityGraph(value = "vorbild.summary", type = EntityGraphType.FETCH)
    Page<Vorbild> findAll(Pageable pageable);
}
