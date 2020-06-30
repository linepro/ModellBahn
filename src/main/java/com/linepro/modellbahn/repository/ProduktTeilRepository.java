package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ProduktTeilRepository")
public interface ProduktTeilRepository extends ItemRepository<ProduktTeil> {

    //@formatter:off
    @Query(value = "SELECT t " +
                   "FROM   produktTeil t " +
                   "WHERE  t.produkt.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                   "AND    t.produkt.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
                   "AND    t.teil.hersteller.name    = :" + ApiNames.TEIL_HERSTELLER + " " + 
                   "AND    t.teil.bestellNr          = :" + ApiNames.TEIL_BESTELL_NR,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "produktTeil", type = EntityGraphType.FETCH)
    Optional<ProduktTeil> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
}
