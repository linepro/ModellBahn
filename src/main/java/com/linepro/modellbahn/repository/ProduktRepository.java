package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ProduktRepository extends ItemRepository<Produkt> {

    //@formatter:off
    @Query(value = "SELECT p " + 
                   "FROM   produkt p " + 
                   "WHERE  p.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                   "AND    p.bestell_nr      = :" + ApiNames.BESTELL_NR,
           nativeQuery = true)
    //@formatter:on
    Optional<Produkt> findByBestellNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);
}
