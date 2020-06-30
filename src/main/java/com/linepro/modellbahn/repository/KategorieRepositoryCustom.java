package com.linepro.modellbahn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Kategorie;

public interface KategorieRepositoryCustom {

    @EntityGraph(value = "kategorie.noChildren", type = EntityGraphType.FETCH)
    Page<Kategorie> findAll(Pageable pageable);

    @EntityGraph(value = "kategorie.noChildren", type = EntityGraphType.FETCH)
    <S extends Kategorie> Page<S> findAll(Example<S> example, Pageable pageable);

    //@formatter:off
    @Query(value = "SELECT k " +
                   "FROM   kategorie k " +
                   "WHERE  k.name IN ( :" + ApiNames.KATEGORIEN + ")",
            nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "kategorie.withChildren", type = EntityGraphType.FETCH)
    List<Kategorie> findKategorien(Optional<List<String>> kategorien);
}
