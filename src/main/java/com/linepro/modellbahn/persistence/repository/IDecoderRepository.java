package com.linepro.modellbahn.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.persistence.util.IItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@Repository
public interface IDecoderRepository extends IItemRepository<Decoder> {
    
    @Query("SELECT d FROM " + DBNames.DECODER + " d WHERE d." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID)
    Decoder findByDecoderId(@Param(ApiNames.DECODER_ID) String decoderId);
}
