package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "VorbildRepository")
public interface VorbildRepository extends VorbildRepositoryCustom, ItemRepository<Vorbild> {

    //@formatter:off
    @Query(value = "SELECT v " +
                   "FROM   vorbild v " +
                   "WHERE  v.gattung.name = :" + ApiNames.GATTUNG,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "vorbild.detail", type = EntityGraphType.FETCH)
    Optional<Vorbild> findByGattung(@Param(ApiNames.GATTUNG) String name);
}
