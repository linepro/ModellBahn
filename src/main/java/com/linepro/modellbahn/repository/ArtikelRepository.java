package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ArtikelRepository")
public interface ArtikelRepository extends ArtikelRepositoryCustom , ItemRepository<Artikel> {

    /*
     * SELECT a
     * FROM Artikel a
     * WHERE a.artikelId = :artikelId
     */
    //@formatter:off
    @Query(value = "SELECT a " + 
                   "FROM   artikel a " + 
                   "WHERE  a.artikelId = :" + ApiNames.ARTIKEL_ID,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "artikel.withChildren", type = EntityGraphType.FETCH)
    Optional<Artikel> findByArtikelId(@Param(ApiNames.ARTIKEL_ID) String artikelId);
}
