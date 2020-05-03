package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderFunktionRepository extends ItemRepository<DecoderFunktion> {

    /*
     * SELECT f
     * FROM   DecoderFunktion f
     * WHERE  f.decoder.decoderId = :decoderId
     * AND    f.funktion.reihe    = :reihe 
     * AND    f.funktion.funktion = :funktion
     * AND    f.decoder.decoderTyp = f.funktion.decoderTyp 
     */
    @Query("SELECT f FROM " + DBNames.DECODER_FUNKTION + 
            " f WHERE f." + DBNames.DECODER + "." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID + 
            " AND f." + DBNames.FUNKTION + "." + DBNames.REIHE + " = :" + ApiNames.REIHE + 
            " AND f." + DBNames.FUNKTION + " = :" + ApiNames.FUNKTION)
    Optional<DecoderFunktion> findByFunktion(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
