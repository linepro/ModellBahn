package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository
public interface KategorieRepository extends NamedItemRepository<Kategorie> {

    @EntityGraph(value = "withChildren", type = EntityGraphType.FETCH)
    Optional<Kategorie> findByName(String name);
}
