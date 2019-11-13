package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderAdressRepository extends IItemRepository<DecoderAdress> {

    /*
     * SELECT a
     * FROM   DecoderAdress a
     * WHERE  a.decoder.decoderId = :decoderId
     * AND    a.index             = :index 
     */
    @Query("SELECT a FROM " + DBNames.DECODER_ADRESS + 
            " a WHERE a." + ApiNames.DECODER + "." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID + 
            " AND a." + DBNames.INDEX + " = :" + ApiNames.INDEX)
    DecoderAdress findByDecoderIdAndIndex(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.INDEX) Integer index);
}
