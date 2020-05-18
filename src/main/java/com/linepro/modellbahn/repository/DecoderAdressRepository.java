package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository
public interface DecoderAdressRepository extends ItemRepository<DecoderAdress> {

    //@formatter:off
    @Query(value = "SELECT a " + 
                   "FROM   decoder_adress a " + 
                   "WHERE  a.decoder.decoder_id = :" + ApiNames.DECODER_ID + " " + 
                   "AND    a.typ.position       = :" + ApiNames.INDEX,
           nativeQuery = true)
    //@formatter:on
    Optional<DecoderAdress> findByIndex(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.INDEX) Integer index);
}
