package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "DecoderCvRepository")
public interface DecoderCvRepository extends ItemRepository<DecoderCv> {

    //@formatter:off
    @Query(value = "SELECT c " + 
                   "FROM   decoderCv c " + 
                   "WHERE  c.decoder.decoderId = :" + ApiNames.DECODER_ID + " " +
                   "AND    c.cv.cv             = :" + ApiNames.CV,
           nativeQuery = false) 
    //@formatter:on
    Optional<DecoderCv>findByCv(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.CV) Integer cv);
}
