package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderTypFunktion;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderTypFunktionRepository extends IItemRepository<DecoderTypFunktion> {

    @Query("SELECT f FROM " + DBNames.DECODER_TYP_FUNKTION + 
            " f WHERE f." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND f." + DBNames.DECODER_TYP + "." + DBNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND f." + DBNames.REIHE + " = :" + ApiNames.REIHE +
            " AND f." + DBNames.FUNKTION + " = :" + ApiNames.FUNKTION)
    DecoderTypFunktion findByTypAndFunktion(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.REIHE) Integer reihe, @Param(ApiNames.FUNKTION) String funktion);
}
