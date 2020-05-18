package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypRepository extends ItemRepository<DecoderTyp> {

    //@formatter:off
    @Query(value = "SELECT t " + 
                   "FROM  decoder_typ t " + 
                   "WHERE t.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                   "AND   t.bestell_nr      = :" + ApiNames.BESTELL_NR,
           nativeQuery = true)
    //@formatter:on
    Optional<DecoderTyp> findByBestellNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);
}
