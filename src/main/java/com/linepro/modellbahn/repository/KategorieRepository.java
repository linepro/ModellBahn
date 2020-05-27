package com.linepro.modellbahn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface KategorieRepository extends NamedItemRepository<Kategorie> {

    @EntityGraph(value = "kategorie.detail", type = EntityGraphType.FETCH)
    Optional<Kategorie> findByName(String name);

    @EntityGraph(value = "kategorie.detail", type = EntityGraphType.FETCH)
    <S extends Kategorie> Page<S> findAll(Example<S> example, Pageable pageable);

    //@formatter:off
    @Query(value = "SELECT k " +
                   "FROM   kategorie k " +
                   "WHERE  k.name IN ( :" + ApiNames.KATEGORIEN + ")",
            nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "kategorie.detail", type = EntityGraphType.FETCH)
    List<Kategorie> findKategorien(Optional<List<String>> kategorien);
}
