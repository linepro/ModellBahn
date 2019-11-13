package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderTypRepository extends IItemRepository<DecoderTyp> {

    @Query("SELECT t FROM " + DBNames.DECODER_TYP + " t WHERE t." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + " AND t." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR)
    DecoderTyp findByHerstellerAndBestelNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);
}
