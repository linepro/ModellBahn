package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypFunktionRepository extends ItemRepository<DecoderTypFunktion> {

    @Query("SELECT f FROM " + DBNames.DECODER_TYP_FUNKTION + 
            " f WHERE f." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND f." + DBNames.DECODER_TYP + "." + DBNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND f." + DBNames.REIHE + " = :" + ApiNames.REIHE +
            " AND f." + DBNames.FUNKTION + " = :" + ApiNames.FUNKTION)
    Optional<DecoderTypFunktion> findByFunktion(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
