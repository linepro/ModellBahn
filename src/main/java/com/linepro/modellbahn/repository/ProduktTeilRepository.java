package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface ProduktTeilRepository extends ItemRepository<ProduktTeil> {

    //@formatter:off
    @Query(value = "SELECT t " +
                   "FROM   produkt_teil t " +
                   "WHERE  t.produkt.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                   "AND    t.produkt.bestell_nr      = :" + ApiNames.BESTELL_NR + " " +
                   "AND    t.teil.hersteller.name    = :" + ApiNames.TEIL_HERSTELLER + " " + 
                   "AND    t.teil.bestell_nr         = :" + ApiNames.TEIL_BESTELL_NR,
           nativeQuery = true)
    //@formatter:on
    Optional<ProduktTeil> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
}
