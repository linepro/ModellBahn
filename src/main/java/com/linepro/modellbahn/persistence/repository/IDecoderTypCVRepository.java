package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderTypCVRepository extends IItemRepository<DecoderTypCV> {

    @Query("SELECT c FROM " + DBNames.DECODER_TYP_CV + 
            " c WHERE c." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND c." + DBNames.DECODER_TYP + "." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND c." + DBNames.CV + " = :" + ApiNames.CV)
    DecoderTypCV findByTypAndCv(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.CV) Integer cv);
}
