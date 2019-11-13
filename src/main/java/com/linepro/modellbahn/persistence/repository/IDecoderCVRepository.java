package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderCVRepository extends IItemRepository<DecoderCV> {

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
    DecoderCV findByDecoderIdAndCv(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.CV) Integer cv);
}
