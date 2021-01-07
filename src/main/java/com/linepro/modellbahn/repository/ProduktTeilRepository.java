package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.repository.base.ItemRepository;

@Repository(PREFIX + "ProduktTeilRepository")
public interface ProduktTeilRepository extends ItemRepository<ProduktTeil> {

    //@formatter:off
    @Query(value = "SELECT t " +
                   "FROM   produktTeil t " +
                   "WHERE  t.produkt.hersteller.name = :" + ApiNames.HERSTELLER + " " + 
                   "AND    t.produkt.bestellNr       = :" + ApiNames.BESTELL_NR + " " +
                   "AND    t.teil.hersteller.name    = :" + ApiNames.TEIL_HERSTELLER + " " + 
                   "AND    t.teil.bestellNr          = :" + ApiNames.TEIL_BESTELL_NR,
           nativeQuery = false)
    //@formatter:on
    @EntityGraph(value = "produktTeil", type = EntityGraphType.FETCH)
    Optional<ProduktTeil> findByTeil(@Param(ApiNames.HERSTELLER) String herstellerStr, @Param(ApiNames.BESTELL_NR) String bestellNr, 
            @Param(ApiNames.TEIL_HERSTELLER) String teilHerstellerStr, @Param(ApiNames.TEIL_BESTELL_NR) String teilBestellNr);
    
    @Query(value = "WITH RECURSIVE components(TEIL_ID, level, path) AS (" +
                   "    SELECT T.TEIL_ID, 0, T.TEIL_ID" +
                   "    FROM   PRODUKT_TEIL T" +
                   "    WHERE  T.TEIL_ID = :" + ApiNames.PRODUKT +
                   "    UNION ALL " +
                   "    SELECT E.TEIL_ID, components.level+1, components.path  || '/' || E.TEIL_ID" +
                   "    FROM PRODUKT_TEIL E JOIN components ON E.PRODUKT_ID = components.TEIL_ID" +
                   "    ORDER BY 2" +
                   ") " +
                   "SELECT path " +
                   "FROM   components " +
                   "WHERE  path LIKE '%' || ':" + ApiNames.TEIL + "' || '%/%' || '" + ApiNames.PRODUKT + "' || '%'", 
           nativeQuery =  true)
    List<String> findTeilen(@Param(ApiNames.PRODUKT) Long produktId, @Param(ApiNames.TEIL) Long teilId);
}
