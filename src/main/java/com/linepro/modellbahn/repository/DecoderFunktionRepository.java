package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.persistence.DBNames.FUNKTION;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository("DecoderFunktionRepository")
public interface DecoderFunktionRepository extends ItemRepository<DecoderFunktion> {

    //@formatter:off
    @Query(value = "SELECT f " + 
                   "FROM   decoderFunktion f " +
                   "WHERE  f.decoder.decoderId = :" + ApiNames.DECODER_ID + " " + 
                   "AND    f.funktion.reihe    = :" + ApiNames.REIHE + " " + 
                   "AND    f.funktion.funktion = :" + FUNKTION,
           nativeQuery = false)
    //@formatter:on
    Optional<DecoderFunktion> findByFunktion(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
