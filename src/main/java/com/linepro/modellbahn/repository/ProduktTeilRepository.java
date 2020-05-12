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
    @Query("SELECT t " +
           "FROM   produktTeil t " +
           "WHERE  t.produkt.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
           "AND    t.produkt.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
           "AND    t.teil.hersteller.name    = :" + ApiNames.TEIL_HERSTELLER + " " + 
           "AND    t.teil.bestellNr          = :" + ApiNames.TEIL_BESTELL_NR)
    //@formatter:on
    Optional<ProduktTeil> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
}
