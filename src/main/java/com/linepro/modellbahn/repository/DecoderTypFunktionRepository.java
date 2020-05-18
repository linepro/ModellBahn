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
    @Query(value = "SELECT f " +
                   "FROM   decoder_typ_funktion f " +
                   "WHERE  f.decoder_typ.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                   "AND    f.decoder_typ.bestell_nr      = :" + ApiNames.BESTELL_NR + " " +
                   "AND    f.reihe                       = :" + ApiNames.REIHE + " " +
                   "AND    f.funktion                    = :" + ApiNames.FUNKTION,
           nativeQuery = true)
    //@formatter:on
    Optional<DecoderTypFunktion> findByFunktion(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
