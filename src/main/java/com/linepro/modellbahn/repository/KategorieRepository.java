package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.repository.base.NamedItemRepository;

@Repository(PREFIX + "KategorieRepository")
public interface KategorieRepository extends NamedItemRepository<Kategorie> {

    @EntityGraph(value = "kategorie.withChildren", type = EntityGraphType.FETCH)
    Optional<Kategorie> findByName(String name);

    @Override
    @EntityGraph(value = "kategorie.noChildren", type = EntityGraphType.FETCH)
    <S extends Kategorie> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @EntityGraph(value = "kategorie.noChildren", type = EntityGraphType.FETCH)
    Page<Kategorie> findAll(Pageable pageable);
}
