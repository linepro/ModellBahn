package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypFunktionRepository extends ItemRepository<DecoderTypFunktion> {

    //@formatter:off
    @Query("SELECT f " +
           "FROM   decoderTypFunktion f " +
           "WHERE  f.decoderTyp.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
           "AND    f.decoderTyp.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
           "AND    f.reihe                      = :" + ApiNames.REIHE + " " +
           "AND    f.funktion                   = :" + ApiNames.FUNKTION)
    //@formatter:on
    Optional<DecoderTypFunktion> findByFunktion(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
