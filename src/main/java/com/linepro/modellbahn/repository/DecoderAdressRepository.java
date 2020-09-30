package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "DecoderAdressRepository")
public interface DecoderAdressRepository extends ItemRepository<DecoderAdress> {

    //@formatter:off
    @Query(value = "SELECT a " + 
                   "FROM   decoderAdress a " + 
                   "WHERE  a.decoder.decoderId = :" + ApiNames.DECODER_ID + " " + 
                   "AND    a.typ.position      = :" + ApiNames.INDEX,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoderAdress", type = EntityGraphType.FETCH)
    Optional<DecoderAdress> findByIndex(@Param(ApiNames.DECODER_ID) String decoderId, @Param(ApiNames.INDEX) Integer index);

    @EntityGraph(value = "decoderAdress", type = EntityGraphType.FETCH)
    Page<DecoderAdress> findAll(Pageable pageable);
}
