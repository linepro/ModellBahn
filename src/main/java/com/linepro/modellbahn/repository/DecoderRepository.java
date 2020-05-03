package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.base.ApiNames;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderRepository extends ItemRepository<Decoder> {
    
    @Query("SELECT d FROM " + DBNames.DECODER + " d WHERE d." + ApiNames.DECODER_ID + " = :" + ApiNames.DECODER_ID)
    Optional<Decoder> findByDecoderId(@Param(ApiNames.DECODER_ID) String decoderId);
}
