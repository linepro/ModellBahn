package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.DBNames.CV;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "DecoderTypCvRepository")
public interface DecoderTypCvRepository extends ItemRepository<DecoderTypCv> {

    //@formatter:off
    @Query(value = "SELECT c " +
                    "FROM   decoderTypCv c " +
                    "WHERE  c.decoderTyp.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                    "AND    c.decoderTyp.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
                    "AND    c.cv                         = :" + CV, 
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "decoderTypCv", type = EntityGraphType.FETCH)
    Optional<DecoderTypCv> findByCv(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, @Param(ApiNames.CV) Integer cv);

    @EntityGraph(value = "decoderTypCv", type = EntityGraphType.FETCH)
    Page<DecoderTypCv> findAll(Pageable pageable);
}
