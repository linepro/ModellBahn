package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderRepository extends ItemRepository<Decoder> {

    //@formatter:off
    @Query(value = "SELECT d " +
                   "FROM   decoder d " +
                   "WHERE  d.decoder_id = :" + ApiNames.DECODER_ID,
           nativeQuery = true)
    //@formatter:on
    Optional<Decoder> findByDecoderId(@Param(ApiNames.DECODER_ID) String decoderId);
}
