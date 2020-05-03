package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderCvRepository extends ItemRepository<DecoderCv> {

    /*
     * SELECT c
     * FROM   DecoderCv c
     * WHERE  c.decoder.decoderId = :decoderId
     * AND    c.cv.cv             = :cv 
     * AND    c.decoder.decoderTyp = c.cv.decoderTyp 
     */
    @Query("SELECT c FROM " + DBNames.DECODER_CV + 
            " c WHERE c." + ApiNames.DECODER + "." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID + 
            " AND c." + DBNames.CV + " = :" + ApiNames.CV)
    Optional<DecoderCv>findByCv(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.CV) Integer cv);
}
