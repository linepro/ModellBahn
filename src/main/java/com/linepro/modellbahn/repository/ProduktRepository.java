package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ProduktRepository")
public interface ProduktRepository extends ProduktRepositoryCustom, ItemRepository<Produkt> {

    //@formatter:off
    @Query(value = "SELECT p " + 
                   "FROM   produkt p " +
                   "WHERE  p.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                   "AND    p.bestellNr       = :" + ApiNames.BESTELL_NR,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "produkt.withChildren", type = EntityGraphType.FETCH)
    Optional<Produkt> findByBestellNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);
}
