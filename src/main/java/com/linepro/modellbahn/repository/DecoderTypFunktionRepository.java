package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "DecoderTypFunktionRepository")
public interface DecoderTypFunktionRepository extends ItemRepository<DecoderTypFunktion> {

    //@formatter:off
    @Query(value = "SELECT f " +
                   "FROM   decoderTypFunktion f " +
                   "WHERE  f.decoderTyp.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                   "AND    f.decoderTyp.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
                   "AND    f.funktion                   = :" + ApiNames.FUNKTION,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoderTypFunktion", type = EntityGraphType.FETCH)
    Optional<DecoderTypFunktion> findByFunktion(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.FUNKTION) String funktion);

    @EntityGraph(value = "decoderTypFunktion", type = EntityGraphType.FETCH)
    Page<DecoderTypFunktion> findAll(Pageable pageable);
}
