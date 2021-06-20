package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "AnderungRepository")
public interface AnderungRepository extends ItemRepository<Anderung> {

    //@formatter:off
    @Query(value = "SELECT a " + 
                   "FROM   anderung a " + 
                   "WHERE  a.artikel.artikelId = :" + ApiNames.ARTIKEL_ID + " " + 
                   "AND    a.anderungId        = :" + ApiNames.ANDERUNG_ID,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "anderung", type = EntityGraphType.FETCH)
    Optional<Anderung> findByAnderungId(@Param(ApiNames.ARTIKEL_ID) String artikelId, @Param(ApiNames.ANDERUNG_ID) Integer anderungId);

    @EntityGraph(value = "anderung", type = EntityGraphType.FETCH)
    Page<Anderung> findAll(Pageable pageable);
}
