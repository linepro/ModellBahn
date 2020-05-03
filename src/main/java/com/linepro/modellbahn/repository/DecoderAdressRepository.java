package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderAdressRepository extends ItemRepository<DecoderAdress> {

    /*
     * SELECT a
     * FROM   DecoderAdress a
     * WHERE  a.decoder.decoderId = :decoderId
     * AND    a.index             = :index 
     */
    @Query("SELECT a FROM " + DBNames.DECODER_ADRESS + 
            " a WHERE a." + ApiNames.DECODER + "." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID + 
            " AND a." + DBNames.INDEX + " = :" + ApiNames.INDEX)
    Optional<DecoderAdress> findByIndex(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.INDEX) Integer index);
}
