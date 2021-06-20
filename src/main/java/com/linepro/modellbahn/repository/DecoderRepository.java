package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

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

@Repository(PREFIX + "DecoderRepository")
public interface DecoderRepository extends ItemRepository<Decoder> {

    //@formatter:off
    @Query(value = "SELECT d " +
                   "FROM   decoder d " +
                   "WHERE  d.decoderId = :" + ApiNames.DECODER_ID,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoder.withChildren", type = EntityGraphType.FETCH)
    Optional<Decoder> findByDecoderId(@Param(ApiNames.DECODER_ID) String decoderId);

    @EntityGraph(value = "decoder.noChildren", type = EntityGraphType.FETCH)
    <S extends Decoder> Page<S> findAll(Example<S> example, Pageable pageable);

    @EntityGraph(value = "decoder.noChildren", type = EntityGraphType.FETCH)
    Page<Decoder> findAll(Pageable pageable);
}
