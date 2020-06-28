package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository("ZugRepository")
public interface ZugRepository extends NamedItemRepository<Zug> {

    //@formatter:off
    @Query(value = "SELECT z " +
                   "FROM   zug z  " +
                   "WHERE  z.name = :" + ApiNames.NAMEN,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "zug.summary", type = EntityGraphType.FETCH)
    Optional<Zug> findByName(String name);

    @EntityGraph(value = "zug.detail", type = EntityGraphType.FETCH)
    <S extends Zug> Page<S> findAll(Example<S> example, Pageable pageable);
}
