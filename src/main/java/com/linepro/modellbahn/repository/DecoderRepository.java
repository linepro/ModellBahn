package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
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
                   "WHERE  d.decoderId = :" + ApiNames.DECODER_ID,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoder.detail", type = EntityGraphType.FETCH)
    Optional<Decoder> findByDecoderId(@Param(ApiNames.DECODER_ID) String decoderId);

    @EntityGraph(value = "decoder.summary", type = EntityGraphType.FETCH)
    <S extends Decoder> Page<S> findAll(Example<S> example, Pageable pageable);
}
