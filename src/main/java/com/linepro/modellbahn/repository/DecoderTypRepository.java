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
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository("DecoderTypRepository")
public interface DecoderTypRepository extends ItemRepository<DecoderTyp> {

    //@formatter:off
    @Query(value = "SELECT t " + 
                   "FROM   decoderTyp t " +
                   "WHERE  t.hersteller.name = :" + ApiNames.HERSTELLER + " " +
                   "AND    t.bestellNr       = :" + ApiNames.BESTELL_NR,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoderTyp.detail", type = EntityGraphType.FETCH)
    Optional<DecoderTyp> findByBestellNr(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr);

    @EntityGraph(value = "decoderTyp.summary", type = EntityGraphType.FETCH)
    <S extends DecoderTyp> Page<S> findAll(Example<S> example, Pageable pageable);
}
