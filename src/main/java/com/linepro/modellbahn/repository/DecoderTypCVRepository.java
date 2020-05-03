package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderTypCVRepository extends ItemRepository<DecoderTypCv> {

    @Query("SELECT c FROM " + DBNames.DECODER_TYP_CV + 
            " c WHERE c." + DBNames.DECODER_TYP + "." + DBNames.HERSTELLER + "." + DBNames.NAME + " = :" + ApiNames.HERSTELLER + 
            " AND c." + DBNames.DECODER_TYP + "." + ApiNames.BESTELL_NR + " = :" + ApiNames.BESTELL_NR +
            " AND c." + DBNames.CV + " = :" + ApiNames.CV)
    Optional<DecoderTypCv> findByCv(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.CV) Integer cv);
}
