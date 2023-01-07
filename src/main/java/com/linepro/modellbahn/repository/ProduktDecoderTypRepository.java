package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ProduktDecoderTyp;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ProduktDecoderTypRepository")
public interface ProduktDecoderTypRepository extends ItemRepository<ProduktDecoderTyp> {

    //@formatter:off
    @Query(value = "SELECT t " +
                   "FROM   produktDecoderTyp t " +
                   "WHERE  t.produkt.hersteller.name    = :" + ApiNames.HERSTELLER + " " + 
                   "AND    t.produkt.bestellNr          = :" + ApiNames.BESTELL_NR + " " +
                   "AND    t.decoderTyp.hersteller.name = :" + ApiNames.TYP_HERSTELLER + " " + 
                   "AND    t.decoderTyp.bestellNr       = :" + ApiNames.TYP_BESTELL_NR,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "produktTeil", type = EntityGraphType.FETCH)
    Optional<ProduktDecoderTyp> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TYP_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TYP_BESTELL_NR) String teilBestellNr);
}
