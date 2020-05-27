package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface VorbildRepository extends ItemRepository<Vorbild> {

    //@formatter:off
    @Query(value = "SELECT v " +
                   "FROM   vorbild v " +
                   "WHERE  v.gattung.name = :" + ApiNames.GATTUNG,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "vorbild.summary", type = EntityGraphType.FETCH)
    Optional<Vorbild> findByGattung(@Param(ApiNames.GATTUNG) String name);

    @EntityGraph(value = "vorbild.detail", type = EntityGraphType.FETCH)
    <S extends Vorbild> Page<S> findAll(Example<S> example, Pageable pageable);
}
