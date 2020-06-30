package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

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

@Repository(PREFIX + "ZugRepository")
public interface ZugRepository extends NamedItemRepository<Zug> {

    //@formatter:off
    @Query(value = "SELECT z " +
                   "FROM   zug z  " +
                   "WHERE  z.name = :" + ApiNames.NAMEN,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "zug.withChildren", type = EntityGraphType.FETCH)
    Optional<Zug> findByName(String name);

    @EntityGraph(value = "zug.noChildren", type = EntityGraphType.FETCH)
    Page<Zug> findAll(Pageable pageable);

    @EntityGraph(value = "zug.noChildren", type = EntityGraphType.FETCH)
    <S extends Zug> Page<S> findAll(Example<S> example, Pageable pageable);
}
